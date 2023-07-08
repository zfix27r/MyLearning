package ru.zfix27r.mylearning.ui.topic.parent

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import ru.zfix27r.mylearning.R
import ru.zfix27r.mylearning.databinding.FragmentTopicParentBinding
import ru.zfix27r.mylearning.ui.BaseBottomSheetDialogFragment
import ru.zfix27r.mylearning.ui.topic.TopicFragment


@AndroidEntryPoint
class TopicParentFragment :
    BaseBottomSheetDialogFragment(R.layout.fragment_topic_parent) {
    private val binding by viewBinding(FragmentTopicParentBinding::bind)
    private val viewModel by viewModels<TopicParentViewModel>()
    private val adapter = TopicParentAdapter {
        object : TopicParentAdapterCallback {
            override fun onClickForward(topicId: Int) {
                viewModel.navToSelf(topicId)
            }

            override fun onClick(parent: TopicParentAdapterModel) {
                finish(parent)
            }

            override fun onLongClick(topicId: Int) {
                navToTopic(topicId)
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener { di ->
            val bottomSheetDialog = di as BottomSheetDialog

            bottomSheetDialog
                .findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
                ?.let {
                    val behaviour = BottomSheetBehavior.from(it)
                    behaviour.isDraggable = false
                    it.updateLayoutParams {
                        val displayHeight = requireActivity().resources.displayMetrics.heightPixels
                        height = displayHeight - toolbar.height
                    }
                    behaviour.state = BottomSheetBehavior.STATE_EXPANDED
                }
        }
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.prepCustomToolbar()
        binding.prepView()
        viewModel.attachToBaseViewModel()
        viewModel.observeUI()
        viewModel.observeTopics()
    }

    private fun FragmentTopicParentBinding.prepCustomToolbar() = apply {
        topicParentNavigation.setOnClickListener {
            if (!viewModel.navPopBack()) findNavController().popBackStack()
        }

        topicParentEdit.setOnClickListener {
            viewModel.isEditing = !viewModel.isEditing
        }

        topicParentAdd.setOnClickListener {
            navToTopic()
        }
    }

    private fun FragmentTopicParentBinding.prepView() = apply {
        topicParentRecycler.adapter = adapter
    }

    private fun TopicParentViewModel.observeUI() =
        uiObservable.observe(viewLifecycleOwner) {
            if (it.isEditing) showEditUI() else hideEditUI()
        }

    private fun showEditUI() {
        binding.topicParentEdit.setIconResource(R.drawable.ic_ui_edit_off)
        binding.topicParentAdd.isVisible = true
        adapter.updateItem()
    }

    private fun hideEditUI() {
        binding.topicParentEdit.setIconResource(R.drawable.ic_ui_edit)
        binding.topicParentAdd.isVisible = false
        adapter.updateItem()
    }

    private fun TopicParentAdapter.updateItem() {
        for (i: Int in 0..itemCount) notifyItemChanged(i, i)
    }

    private fun TopicParentViewModel.observeTopics() =
        topics.observe(viewLifecycleOwner) {
            it.setNavigationIcon()
            adapter.submitList(it)
        }

    private fun List<TopicParentAdapterModel>.setNavigationIcon() {
        if (isEmpty() || first().parentId == 0)
            binding.topicParentNavigation.setIconResource(R.drawable.ic_ui_close)
        else
            binding.topicParentNavigation.setIconResource(R.drawable.ic_ui_arrow_back)
    }

    private fun finish(topic: TopicParentAdapterModel) {
        if (viewModel.topicParentId != topic.id) {
            prevStackSavedState[TopicFragment.TOPIC_PARENT_ID] = topic.id
            prevStackSavedState[TopicFragment.TOPIC_PARENT_TITLE] = topic.title
            prevStackSavedState[TopicFragment.TOPIC_PARENT_SUBTITLE] = topic.subtitle
        } else {
            prevStackSavedState[TopicFragment.TOPIC_PARENT_ID] = 0
            prevStackSavedState[TopicFragment.TOPIC_PARENT_TITLE] = ""
            prevStackSavedState[TopicFragment.TOPIC_PARENT_SUBTITLE] = ""
        }

        navPopBack()
    }

    private fun navToTopic(topicId: Int = 0) {
        val direction = TopicParentFragmentDirections.actionTopicParentToTopic(topicId)
        findNavController().navigate(direction)
    }
}