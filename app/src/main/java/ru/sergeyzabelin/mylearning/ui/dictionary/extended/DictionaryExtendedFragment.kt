package ru.sergeyzabelin.mylearning.ui.dictionary.extended

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.sergeyzabelin.mylearning.R
import ru.sergeyzabelin.mylearning.data.DictionaryPreferences
import ru.sergeyzabelin.mylearning.data.model.db.Topic
import ru.sergeyzabelin.mylearning.databinding.FragmentDictionaryExtendedBinding
import ru.sergeyzabelin.mylearning.ui.common.RetryCallback
import ru.sergeyzabelin.mylearning.utils.autoCleared


@AndroidEntryPoint
class DictionaryExtendedFragment : Fragment() {

    private val viewModel by viewModels<DictionaryExtendedViewModel>()
    private var binding by autoCleared<FragmentDictionaryExtendedBinding>()
    private var adapter by autoCleared<DictionaryExtendedAdapter>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dataBinding = FragmentDictionaryExtendedBinding.inflate(inflater, container, false)

        dataBinding.retryCallback = object : RetryCallback {
            override fun retry() {
                Log.e("retry", "on")
            }
        }

        binding = dataBinding
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.dictionary_app_bar, menu)
                menu.findItem(R.id.switcher).isChecked = true
                view.visibility
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.switcher -> toSimpleFragment()
                    R.id.editor -> toEditorFragment()
                    android.R.id.home -> findNavController().popBackStack()
                }

                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        binding.lifecycleOwner = viewLifecycleOwner

        adapter = DictionaryExtendedAdapter(
            { id -> onClickGoNextTopic(id) },
            { id -> onClickGoTopicQuote(id) },
            { topic -> onLongClickActionMode(topic) })

        binding.mainRecycler.adapter = adapter

        viewModel.data.observe(viewLifecycleOwner) { list ->
            list.data?.let { data ->
                (activity as AppCompatActivity).supportActionBar?.let {
                    it.title = data.topic.title
                    it.subtitle = data.topic.subTitle
                }

                adapter.submitList(data.topics)
            }
        }
    }

    private fun onClickGoNextTopic(id: Long) {
        findNavController().navigate(
            DictionaryExtendedFragmentDirections
                .actionDictionaryExtendedFragmentSelf(id)
        )
    }

    private fun onClickGoTopicQuote(id: Long) {

    }

    private fun onLongClickActionMode(topic: Topic) {
    }

    private fun toSimpleFragment() {
        viewModel.setMode(DictionaryPreferences.MODE.SIMPLE)
        findNavController().navigate(
            DictionaryExtendedFragmentDirections
                .actionDictionaryExtendedFragmentToDictionaryFragment(
                    viewModel.savedTopicId
                )
        )
    }

    private fun toEditorFragment() {
        findNavController().navigate(
            DictionaryExtendedFragmentDirections
                .actionDictionaryExtendedFragmentToDictionaryEditorFragment(
                    viewModel.savedTopicId
                )
        )
    }
}
