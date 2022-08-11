package ru.sergeyzabelin.mylearning.ui.dictionary

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.sergeyzabelin.mylearning.R
import ru.sergeyzabelin.mylearning.databinding.FragmentDictionaryBinding
import ru.sergeyzabelin.mylearning.utils.autoCleared
import ru.zfix27r.domain.model.DictionaryResModel

typealias TopicMain = DictionaryResModel.Success.TopicMain
typealias TopicSub = DictionaryResModel.Success.TopicSub

@AndroidEntryPoint
class DictionaryFragment : Fragment() {

    private val viewModel by viewModels<DictionaryViewModel>()
    private var binding by autoCleared<FragmentDictionaryBinding>()
    private var adapter by autoCleared<DictionaryAdapter>()
    private val args by navArgs<DictionaryFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dataBinding = FragmentDictionaryBinding.inflate(inflater, container, false)
        dataBinding.lifecycleOwner = viewLifecycleOwner
/*        dataBinding.retryCallback = object : RetryCallback {
            override fun retry() {
                // TODO #1 Повторная попытка загрузки данных. Пока не ясно как ее реализовать, приложение рабоает только с внутренней БД.
            }
        }*/

        binding = dataBinding
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actionBar()
        adapter()
        loadDictionary()
    }

    private fun actionBar() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.dictionary_app_bar, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.add -> findNavController().navigate(
                        DictionaryFragmentDirections
                            .actionDictionaryToTopicEditor(topicId = args.topicId)
                    )
                    android.R.id.home -> findNavController().popBackStack()
                }

                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun adapter() {
        adapter = DictionaryAdapter(object : DictionaryActionListener {
            override fun onSelf(topicId: Long) {
                findNavController().navigate(
                    DictionaryFragmentDirections.actionDictionaryToDictionary(topicId = topicId)
                )
            }

            override fun onDetails(topicId: Long) {
                findNavController().navigate(
                    DictionaryFragmentDirections.actionDictionaryToContent(topicId = topicId)
                )
            }

            override fun onEdit(topicId: Long) {
                findNavController().navigate(
                    DictionaryFragmentDirections.actionDictionaryToTopicEditor(topicId = topicId)
                )
            }

            override fun onDelete(topicId: Long) {

            }
        })

        binding.recycler.adapter = adapter
    }

    private fun loadDictionary() {
        lifecycleScope.launch {
            viewModel.getDictionary(args.topicId).collect {
                when (it) {
                    is DictionaryResModel.Success -> {
                        setToolbarTitles(it.topic)
                        adapter.submitList(it.topics)
                    }
                    is DictionaryResModel.Fail -> {
                        binding.recycler.visibility = View.GONE
                        binding.noResult.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun setToolbarTitles(topic: TopicMain) {
        (activity as AppCompatActivity).supportActionBar?.let { actionBar ->
            actionBar.title = topic.title
            actionBar.subtitle = topic.subTitle
        }
    }

}
