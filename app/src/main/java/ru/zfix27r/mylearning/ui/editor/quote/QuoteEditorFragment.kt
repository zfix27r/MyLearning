package ru.zfix27r.mylearning.ui.editor.quote

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
import ru.zfix27r.mylearning.databinding.FragmentQuoteEditorBinding
import ru.zfix27r.mylearning.ui.BaseFragment
import ru.zfix27r.mylearning.ui.BaseViewModelEvent
import ru.zfix27r.mylearning.ui.editor.topic.TopicEditorFragment
import ru.zfix27r.mylearning.ui.editor.updateTextInputLayout

@AndroidEntryPoint
class QuoteEditorFragment : BaseFragment(R.layout.fragment_quote_editor) {
    private val binding by viewBinding(FragmentQuoteEditorBinding::bind)
    private val viewModel by viewModels<QuoteEditorViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
        setListeners()
        viewModel.attachViewModel()
        viewModel.observeEvent()
        currentStackSavedState.observeTopicParent()
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
        binding.quoteEditorDescription.editText?.addTextChangedListener { it.updateDescription() }
        binding.quoteEditorTopic.setOnClickListener { navToTopicEditorParent() }
    }

    private fun QuoteEditorViewModel.observeEvent() {
        event.observe(viewLifecycleOwner) {
            when (it) {
                is BaseViewModelEvent.UpdateUI -> updateUI()
                is BaseViewModelEvent.Finish -> onFinish()
                else -> {}
            }
        }
    }

    private fun SavedStateHandle.observeTopicParent() {
        getLiveData<Int>(TopicEditorFragment.TOPIC_PARENT_ID).observe(viewLifecycleOwner) {
            viewModel.topicId = it ?: 0
        }
        getLiveData<String>(TopicEditorFragment.TOPIC_PARENT_TITLE).observe(viewLifecycleOwner) {
            viewModel.topicTitle = it ?: ""
            updateUITitle()
        }
        getLiveData<String>(TopicEditorFragment.TOPIC_PARENT_SUBTITLE).observe(viewLifecycleOwner) {
            viewModel.topicSubtitle = it ?: ""
            updateUISubtitle()
        }
    }

    private fun Editable?.updateDescription() {
        this?.let {
            viewModel.quoteDescription = it.toString()
            binding.quoteEditorDescription.updateTextInputLayout(viewModel.statusDescription)
        }
    }

    private fun updateUI() {
        binding.quoteEditorDescriptionText.setText(viewModel.quoteDescription)
        updateUITitle()
        updateUISubtitle()
    }

    private fun updateUITitle() {
        if (viewModel.topicTitle.isNotEmpty())
            binding.quoteEditorTopicTitle.text = viewModel.topicTitle
        else
            binding.quoteEditorTopicTitle.setText(R.string.topic_editor_parent_id_null)
    }

    private fun updateUISubtitle() {
        binding.quoteEditorTopicSubtitle.isVisible = viewModel.topicSubtitle.isNotEmpty()
        binding.quoteEditorTopicSubtitle.text = viewModel.topicSubtitle
    }

    private fun onFinish() {
        navPopBack()
    }

    private fun navToTopicEditorParent() {
        val direction = QuoteEditorFragmentDirections
            .actionQuoteEditorToTopicEditorParent(topicParentId = viewModel.topicId)
        findNavController().navigate(direction)
    }
}