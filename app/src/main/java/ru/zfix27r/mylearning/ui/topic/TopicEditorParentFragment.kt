package ru.zfix27r.mylearning.ui.topic

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.zfix27r.mylearning.R
import ru.zfix27r.mylearning.databinding.FragmentTopicEditorParentBinding
import ru.zfix27r.mylearning.ui.MainActivity
import ru.zfix27r.mylearning.ui.ToolbarMode

@AndroidEntryPoint
class TopicEditorParentFragment : Fragment(R.layout.fragment_topic_editor_parent),
    TopicEditorParentAdapterCallback {
    private val binding by viewBinding(FragmentTopicEditorParentBinding::bind)
    private val viewModel by viewModels<TopicEditorParentViewModel>()
    private val args by navArgs<TopicEditorParentFragmentArgs>()
    private val mainActivity by lazy {
        requireActivity() as? MainActivity ?: throw ExceptionInInitializerError()
    }

    private val adapter = TopicEditorParentAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareToolbar()
        prepareView()

        observersUI()
    }

    private fun prepareToolbar() {
        mainActivity.toolbar(ToolbarMode.EMPTY)
        mainActivity.toolbarHome {
            val backQueue = findNavController().backQueue
            val previousDestination = backQueue[backQueue.size - 2].destination

            val topics = viewModel.topics.value!!
            val previousParentId =
                if (topics.isNotEmpty()) topics.first().parentId else 0

            if (previousParentId > 0
                && previousDestination.id != R.id.topic_editor_parent
            ) navigateToPrevSelf(previousParentId)
            else findNavController().popBackStack()
        }
    }

    private fun prepareView() {
        args.run { viewModel.loading(topicId, topicIdSelf, topicIdChecked, isTopicParentId) }
        binding.topicEditorParentParentRecycler.adapter = adapter
    }

    private fun observersUI() {
        viewModel.topics.observe(viewLifecycleOwner) { adapter.submitList(it) }
    }

    override fun navigateToSelf(topicId: Int) {
        val direction = TopicEditorParentFragmentDirections
            .actionTopicEditorParentSelf(
                topicId = topicId,
                topicIdSelf = viewModel.topicIdSelf,
                topicIdChecked = viewModel.topicIdChecked,
                isTopicParentId = false
            )
        findNavController().navigate(direction)
    }

    override fun onParentChecked(topic: TopicEditorParentUIModel) {
        viewModel.returnParentToTopicEditor(findNavController(), topic)
    }

    private fun navigateToPrevSelf(topicId: Int) {
        val direction = TopicEditorParentFragmentDirections
            .actionTopicEditorParentSelf(topicId, viewModel.topicIdChecked)
        val options = NavOptions.Builder()
            .setPopUpTo(R.id.topic_editor_parent, true)
            .build()
        findNavController().navigate(direction, options)
    }
}