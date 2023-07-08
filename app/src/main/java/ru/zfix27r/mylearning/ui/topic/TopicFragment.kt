package ru.zfix27r.mylearning.ui.topic

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.zfix27r.mylearning.R
import ru.zfix27r.mylearning.databinding.FragmentTopicBinding
import ru.zfix27r.mylearning.ui.BaseFragment
import ru.zfix27r.mylearning.ui.BaseViewModelEvent
import ru.zfix27r.mylearning.ui.KeyboardTriggerBehavior
import ru.zfix27r.mylearning.ui.topicIcons

@AndroidEntryPoint
class TopicFragment : BaseFragment(R.layout.fragment_topic) {
    private val binding by viewBinding(FragmentTopicBinding::bind)
    private val viewModel by viewModels<TopicViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbar()
        binding.topicContainer.setInsets()
        viewModel.attachViewModel()
        viewModel.observeEvent()
        currentStackSavedState.observeIcon()
        currentStackSavedState.observeParent()
        binding.setListeners()
        binding.observeKeyboard()
    }

    private fun setToolbar() {
        disableScrollTopAppBar()
        toolbar.updateMenu(R.menu.toolbar_empty)
    }

    private fun TopicViewModel.observeEvent() {
        event.observe(viewLifecycleOwner) {
            when (it) {
                is BaseViewModelEvent.UpdateUI -> updateUI()
                else -> {}
            }
        }
    }

    private fun SavedStateHandle.observeIcon() {
        getLiveData<Int>(TOPIC_ICON_ID).observe(viewLifecycleOwner) {
            viewModel.topicIconId = it
            updateUIIcon()
        }
    }

    private fun SavedStateHandle.observeParent() {
        getLiveData<Int>(TOPIC_PARENT_ID).observe(viewLifecycleOwner) {
            viewModel.topicParentId = it ?: 0
        }
        getLiveData<String>(TOPIC_PARENT_TITLE).observe(viewLifecycleOwner) {
            viewModel.topicParentTitle = it ?: ""
            updateUIParentTitle()
        }
        getLiveData<String>(TOPIC_PARENT_SUBTITLE).observe(viewLifecycleOwner) {
            viewModel.topicParentSubtitle = it ?: ""
            updateUIParentSubtitle()
        }
    }

    private fun FragmentTopicBinding.setListeners() {
        topicIcon.setOnClickListener { navToTopicEditorIcon() }
        topicTopicParent.setOnClickListener { navToTopicParent() }
    }

    private fun FragmentTopicBinding.observeKeyboard() {
        KeyboardTriggerBehavior(requireActivity()).observe(viewLifecycleOwner) {
            when (it) {
                KeyboardTriggerBehavior.Status.OPEN -> {}
                KeyboardTriggerBehavior.Status.CLOSED -> {
                    topicTitleText.clearFocus()
                    topicSubtitleText.clearFocus()
                }
            }
        }
    }

    private fun updateUI() {
        binding.topicTitleText.setText(viewModel.topicTitle)
        binding.topicSubtitleText.setText(viewModel.topicSubtitle)

        updateUIIcon()
        updateUIParentTitle()
        updateUIParentSubtitle()
    }

    private fun updateUIIcon() {
        binding.topicIcon.setImageResource(topicIcons[viewModel.topicIconId].second)
    }

    private fun updateUIParentTitle() {
        if (viewModel.topicParentTitle != "")
            binding.topicTopicParentTitle.text = viewModel.topicParentTitle
        else
            binding.topicTopicParentTitle.setText(R.string.topic_editor_parent_id_null)
    }

    private fun updateUIParentSubtitle() {
        binding.topicTopicParentSubtitle.isVisible = viewModel.topicParentSubtitle != ""
        binding.topicTopicParentSubtitle.text = viewModel.topicParentSubtitle
    }

    override fun onPause() {
        super.onPause()

        val title = binding.topicTitle.editText?.text.toString()
        val subtitle = binding.topicSubtitle.editText?.text.toString()
        viewModel.save(title, subtitle)
    }

    private fun navToTopicParent() {
        val direction = TopicFragmentDirections.actionTopicToTopicParent(
            topicId = viewModel.topicId ?: 0,
            topicParentId = viewModel.topicParentId
        )
        findNavController().navigate(direction)
    }

    private fun navToTopicEditorIcon() {
        val direction = TopicFragmentDirections.actionTopicToTopicIcon(viewModel.topicIconId)
        findNavController().navigate(direction)
    }

    companion object {
        const val TOPIC_PARENT_ID = "parent_topic_id"
        const val TOPIC_PARENT_TITLE = "parent_topic_title"
        const val TOPIC_PARENT_SUBTITLE = "parent_topic_subtitle"

        const val TOPIC_ICON_ID = "topic_icon_id"
    }
}