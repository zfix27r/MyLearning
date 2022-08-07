package ru.sergeyzabelin.mylearning.ui.dictionary

import android.os.Bundle
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
import ru.zfix27r.data.common.Status
import ru.zfix27r.data.model.db.Topic
import ru.sergeyzabelin.mylearning.databinding.FragmentDictionaryBinding
import ru.sergeyzabelin.mylearning.ui.common.RetryCallback
import ru.sergeyzabelin.mylearning.utils.autoCleared

@AndroidEntryPoint
class DictionaryFragment : Fragment() {

    private val viewModel by viewModels<DictionaryViewModel>()
    private var binding by autoCleared<FragmentDictionaryBinding>()
    private var adapter by autoCleared<DictionaryAdapter>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dataBinding = FragmentDictionaryBinding.inflate(inflater, container, false)
        dataBinding.lifecycleOwner = viewLifecycleOwner
        dataBinding.retryCallback = object : RetryCallback {
            override fun retry() {
                // TODO #1 Повторная попытка загрузки данных. Пока не ясно как ее реализовать, приложение рабоает только с внутренней БД.
            }
        }

        binding = dataBinding
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        actionBar()
        adapter()
        dataObserver()
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
                            .actionDictionaryToTopicEditor(0, viewModel.topicId)
                    )
                    android.R.id.home -> findNavController().popBackStack()
                }

                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun adapter() {
        adapter = DictionaryAdapter(object : TopicActionListener {
            override fun onSelf(topic: Topic) {
                findNavController().navigate(
                    DictionaryFragmentDirections.actionDictionaryToDictionary(topic.id)
                )
            }

            override fun onDetails(topic: Topic) {
                findNavController().navigate(
                    DictionaryFragmentDirections.actionDictionaryToContent(topic.id)
                )
            }

            override fun onAdd(topic: Topic) {
                findNavController().navigate(
                    DictionaryFragmentDirections
                        .actionDictionaryToTopicEditor(
                            topic.id,
                            topic.parentTopicId
                        )
                )
            }

            override fun onEdit(topic: Topic) {
                findNavController().navigate(
                    DictionaryFragmentDirections
                        .actionDictionaryToTopicEditor(
                            topic.id,
                            topic.parentTopicId
                        )
                )
            }

            override fun onDelete(topic: Topic) {

            }
        })

        binding.recycler.adapter = adapter
    }

    private fun dataObserver() {
        viewModel.data.observe(viewLifecycleOwner) { list ->
            when (list.status) {
                Status.LOADING -> {}
                Status.ERROR -> {}
                Status.SUCCESS -> {
                    list.data?.topics?.let { topics ->
                        if (topics.isEmpty()) {
                            binding.recycler.visibility = View.GONE
                            binding.noResult.visibility = View.VISIBLE
                        } else {
                            (activity as AppCompatActivity).supportActionBar?.let {
                                it.title = list.data.topic.title
                                it.subtitle = list.data.topic.subTitle
                            }

                            adapter.submitList(list.data.topics)
                        }
                    }
                }
            }
        }
    }

}
