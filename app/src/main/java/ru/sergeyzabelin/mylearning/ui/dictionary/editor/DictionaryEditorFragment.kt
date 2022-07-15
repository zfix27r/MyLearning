package ru.sergeyzabelin.mylearning.ui.dictionary.editor

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
import ru.sergeyzabelin.mylearning.data.model.db.Topic
import ru.sergeyzabelin.mylearning.databinding.FragmentDictionaryEditorBinding
import ru.sergeyzabelin.mylearning.ui.common.RetryCallback
import ru.sergeyzabelin.mylearning.utils.autoCleared


@AndroidEntryPoint
class DictionaryEditorFragment : Fragment() {

    private val viewModel by viewModels<DictionaryEditorViewModel>()
    private var binding by autoCleared<FragmentDictionaryEditorBinding>()
    private var adapter by autoCleared<DictionaryEditorAdapter>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dataBinding = FragmentDictionaryEditorBinding.inflate(inflater, container, false)

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
                menu.findItem(R.id.switcher).isVisible = false
                menu.findItem(R.id.editor).isVisible = false
                view.visibility
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    android.R.id.home -> findNavController().popBackStack()
                }

                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.fab.visibility = View.VISIBLE

        adapter = DictionaryEditorAdapter(
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
            DictionaryEditorFragmentDirections.actionDictionaryEditorFragmentSelf(id)
        )
    }

    private fun onClickGoTopicQuote(id: Long) {

    }

    private fun onLongClickActionMode(topic: Topic) {
/*        val callback = object : ActionMode.Callback {

            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                activity?.menuInflater?.inflate(R.menu.dictionary_contextual_action_bar, menu)
                menu?.let {
                    it.findItem(R.id.done)?.isVisible = false
                    if (topic.isHasChild) it.findItem(R.id.delete)?.isVisible = false
                }
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {

                return false
            }

            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                return when (item?.itemId) {
                    R.id.edit -> {
                        mode?.finish()
                        findNavController().navigate(
                            DictionaryFragmentDirections
                                .actionDictionaryFragmentToDictionaryTopicEditFragment(topic.id)
                        )
                        true
                    }
                    R.id.delete -> {
                        mode?.let {
                            it.title = getString(R.string.message_delete_order_confirmation)
                            it.menu.findItem(R.id.edit).isVisible = false
                            it.menu.findItem(R.id.delete).isVisible = false
                            it.menu.findItem(R.id.done).isVisible = true
                        }
                        true
                    }
                    R.id.done -> {
                        viewModel.topicDelete(topic)
                        mode?.finish()
                        true
                    }
                    else -> false
                }
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
            }
        }

        activity?.startActionMode(callback)*/
    }
}
