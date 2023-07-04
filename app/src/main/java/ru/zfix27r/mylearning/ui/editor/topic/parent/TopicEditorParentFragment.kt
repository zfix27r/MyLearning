package ru.zfix27r.mylearning.ui.editor.topic.parent

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.zfix27r.mylearning.R
import ru.zfix27r.mylearning.databinding.FragmentTopicEditorParentBinding
import ru.zfix27r.mylearning.ui.BaseBottomSheetDialogFragment
import ru.zfix27r.mylearning.ui.editor.topic.TopicEditorFragment

@AndroidEntryPoint
class TopicEditorParentFragment :
    BaseBottomSheetDialogFragment(R.layout.fragment_topic_editor_parent) {
    private val binding by viewBinding(FragmentTopicEditorParentBinding::bind)
    private val viewModel by viewModels<TopicEditorParentViewModel>()
    private val adapter = TopicEditorParentAdapter {
        object : TopicEditorParentAdapterCallback {
            override fun onClickNext(topicId: Int) {
                viewModel.navToSelf(topicId)
            }

            override fun onClick(parent: TopicEditorParentAdapterModel) {
                finish(parent)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbar()
        setView()
        observeViewModel()
    }

    private fun setToolbar() {
        binding.topicEditorParentToolbar.setNavigationOnClickListener {
            if (!viewModel.navPopBack()) findNavController().popBackStack()
        }
    }

    private fun setView() {
        binding.topicEditorParentRecycler.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.attachViewModel()
        viewModel.topics.observe(viewLifecycleOwner) {
            it.setNavigationIcon()
            adapter.submitList(it)
        }
    }

    private fun List<TopicEditorParentAdapterModel>.setNavigationIcon() {
        if (isEmpty() || first().parentId == 0)
            binding.topicEditorParentToolbar.setNavigationIcon(R.drawable.ic_ui_close)
        else
            binding.topicEditorParentToolbar.setNavigationIcon(R.drawable.ic_ui_arrow_back)
    }

    private fun finish(topic: TopicEditorParentAdapterModel) {
        if (viewModel.initTopicParentId != topic.id) {
            prevStackSavedState[TopicEditorFragment.TOPIC_PARENT_ID] = topic.id
            prevStackSavedState[TopicEditorFragment.TOPIC_PARENT_TITLE] = topic.title
            prevStackSavedState[TopicEditorFragment.TOPIC_PARENT_SUBTITLE] = topic.subtitle
        } else {
            prevStackSavedState[TopicEditorFragment.TOPIC_PARENT_ID] = 0
            prevStackSavedState[TopicEditorFragment.TOPIC_PARENT_TITLE] = ""
            prevStackSavedState[TopicEditorFragment.TOPIC_PARENT_SUBTITLE] = ""
        }

        navPopBack()
    }
}