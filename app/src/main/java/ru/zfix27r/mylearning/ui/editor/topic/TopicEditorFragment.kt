package ru.zfix27r.mylearning.ui.editor.topic

import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.zfix27r.mylearning.R
import ru.zfix27r.mylearning.databinding.FragmentTopicEditorBinding
import ru.zfix27r.mylearning.ui.BaseFragment
import ru.zfix27r.mylearning.ui.BaseViewModelEvent
import ru.zfix27r.mylearning.ui.editor.updateTextInputLayout
import ru.zfix27r.mylearning.ui.topicIcons

@AndroidEntryPoint
class TopicEditorFragment : BaseFragment(R.layout.fragment_topic_editor) {
    private val binding by viewBinding(FragmentTopicEditorBinding::bind)
    private val viewModel by viewModels<TopicEditorViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbar()
        setListeners()
        viewModel.attachViewModel()
        viewModel.observeEvent()
        currentStackSavedState.observeIcon()
        currentStackSavedState.observeParent()
    }

    private fun setToolbar() {
        toolbar.updateMenu(R.menu.toolbar_editor) {
            when (it.itemId) {
                R.id.toolbar_done -> {
                    viewModel.trySave()
                    true
                }

                else -> false
            }
        }
    }

    private fun setListeners() {
        binding.run {
            topicEditorPreview.setOnClickListener { navToTopicEditorIcon() }
            topicEditorTitle.editText?.addTextChangedListener { it.updateTitle() }
            topicEditorSubtitle.editText?.addTextChangedListener { it.updateSubtitle() }
            topicEditorTopicParent.setOnClickListener { navToTopicEditorParent() }
        }
    }

    private fun TopicEditorViewModel.observeEvent() {
        event.observe(viewLifecycleOwner) {
            when (it) {
                is BaseViewModelEvent.UpdateUI -> updateUI()
                is BaseViewModelEvent.Finish -> onFinish()
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

    /* UI */
    private fun updateUI() {
        binding.topicEditorTitleText.setText(viewModel.topicTitle)
        binding.topicEditorSubtitleText.setText(viewModel.topicSubtitle)

        updateUIIcon()
        updateUITitle()
        updateUISubtitle()
        updateUIParentTitle()
        updateUIParentSubtitle()
    }

    private fun updateUIIcon() {
        binding.topicEditorPreviewIcon.setImageResource(topicIcons[viewModel.topicIconId].second)
    }

    private fun updateUITitle() {
        binding.topicEditorPreviewTitle.text = viewModel.topicTitle
    }

    private fun updateUISubtitle() {
        binding.topicEditorPreviewSubtitle.isVisible = viewModel.topicSubtitle.isNotEmpty()
        binding.topicEditorPreviewSubtitle.text = viewModel.topicSubtitle
    }

    private fun updateUIParentTitle() {
        if (viewModel.topicParentTitle.isNotEmpty())
            binding.topicEditorTopicParentTitle.text = viewModel.topicParentTitle
        else
            binding.topicEditorTopicParentTitle.setText(R.string.topic_editor_parent_id_null)
    }

    private fun updateUIParentSubtitle() {
        binding.topicEditorTopicParentSubtitle.isVisible =
            viewModel.topicParentSubtitle.isNotEmpty()
        binding.topicEditorTopicParentSubtitle.text = viewModel.topicParentSubtitle
    }

    private fun Editable?.updateTitle() {
        this?.let {
            viewModel.topicTitle = it.toString()
            binding.topicEditorTitle.updateTextInputLayout(viewModel.statusTitle)
            updateUITitle()
        }
    }

    private fun Editable?.updateSubtitle() {
        this?.let {
            viewModel.topicSubtitle = it.toString()
            updateUISubtitle()
        }
    }

    private fun onFinish() {
        navPopBack()
    }

    /* NAVIGATION */
    private fun navToTopicEditorParent() {
        val direction = TopicEditorFragmentDirections
            .actionTopicEditorToTopicEditorParent(
                topicId = viewModel.topicId ?: 0,
                topicParentId = viewModel.topicParentId
            )
        findNavController().navigate(direction)
    }

    private fun navToTopicEditorIcon() {
        val direction =
            TopicEditorFragmentDirections
                .actionTopicEditorToTopicEditorIcon(viewModel.topicIconId)
        findNavController().navigate(direction)
    }

    companion object {
        const val TOPIC_PARENT_ID = "parent_topic_id"
        const val TOPIC_PARENT_TITLE = "parent_topic_title"
        const val TOPIC_PARENT_SUBTITLE = "parent_topic_subtitle"

        const val TOPIC_ICON_ID = "topic_icon_id"
    }
}