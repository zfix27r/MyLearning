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
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.sergeyzabelin.mylearning.R
import ru.sergeyzabelin.mylearning.databinding.FragmentDictionaryBinding
import ru.zfix27r.domain.model.DictionaryResModel

typealias TopicMain = DictionaryResModel.Data.TopicMain
typealias TopicSub = DictionaryResModel.Data.TopicSub

@AndroidEntryPoint
class DictionaryFragment : Fragment() {

    private val viewModel by viewModels<DictionaryViewModel>()
    private val binding by viewBinding(FragmentDictionaryBinding::bind)
    private val args by navArgs<DictionaryFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadTopic()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_dictionary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        actionBar()
        adapter()
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
                            .actionDictionaryToTopicEditor(parentId = args.topicId)
                    )
                    android.R.id.home -> findNavController().popBackStack()
                }

                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun adapter() {
        val adapter = DictionaryAdapter(object : DictionaryActionListener {
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

        viewModel.topic.observe(viewLifecycleOwner) {
            setToolbarTitles(it.topic)
            if (it.topics.isEmpty()) {
                binding.recycler.visibility = View.GONE
                binding.noResult.visibility = View.VISIBLE
            } else adapter.submitList(it.topics)
        }
    }

    private fun setToolbarTitles(topic: TopicMain) {
        (activity as AppCompatActivity).supportActionBar?.let { actionBar ->
            actionBar.title = topic.title
            actionBar.subtitle = topic.subTitle
        }
    }
}
