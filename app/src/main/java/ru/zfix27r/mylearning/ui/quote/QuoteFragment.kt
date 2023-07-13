package ru.zfix27r.mylearning.ui.quote

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.zfix27r.mylearning.R
import ru.zfix27r.mylearning.databinding.FragmentQuoteBinding
import ru.zfix27r.mylearning.ui.base.BaseFragment
import ru.zfix27r.mylearning.ui.base.BaseViewModelEvent
import ru.zfix27r.mylearning.ui.activity.KeyboardTriggerBehavior
import ru.zfix27r.mylearning.ui.getResIdTopicIcon
import ru.zfix27r.mylearning.ui.setTextOrGone
import ru.zfix27r.mylearning.ui.setTextOrUndefined
import ru.zfix27r.mylearning.ui.topic.TopicFragment

@AndroidEntryPoint
class QuoteFragment : BaseFragment(R.layout.fragment_quote) {
    private val binding by viewBinding(FragmentQuoteBinding::bind)
    private val viewModel by viewModels<QuoteViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbar()
        binding.quoteContainer.setInsets()
        viewModel.attachToBaseViewModel()
        viewModel.observeEvent()
        currentStackSavedState.observeTopicParent()
        binding.setListeners()
        binding.observeKeyboard()
    }

    private fun setToolbar() {
        disableScrollTopAppBar()
        searchbar.updateMenu(R.menu.toolbar_empty)
    }

    private fun QuoteViewModel.observeEvent() {
        event.observe(viewLifecycleOwner) {
            when (it) {
                is BaseViewModelEvent.UpdateUI -> updateUI()
                else -> {}
            }
        }
    }

    private fun SavedStateHandle.observeTopicParent() {
        getLiveData<Int>(TopicFragment.TOPIC_PARENT_ID).observe(viewLifecycleOwner) {
            viewModel.topicId = it ?: 0
        }
        getLiveData<String>(TopicFragment.TOPIC_PARENT_TITLE).observe(viewLifecycleOwner) {
            viewModel.topicTitle = it ?: ""
            binding.quoteTopicTitle.setTextOrUndefined(viewModel.topicTitle)
        }
        getLiveData<String>(TopicFragment.TOPIC_PARENT_SUBTITLE).observe(viewLifecycleOwner) {
            viewModel.topicSubtitle = it ?: ""
            binding.quoteTopicSubtitle.setTextOrGone(viewModel.topicSubtitle)
        }
    }

    private fun FragmentQuoteBinding.setListeners() {
        quoteTopic.setOnClickListener { navToTopic() }
    }

    private fun FragmentQuoteBinding.observeKeyboard() {
        KeyboardTriggerBehavior(requireActivity()).observe(viewLifecycleOwner) {
            when (it) {
                KeyboardTriggerBehavior.Status.OPEN -> {}
                KeyboardTriggerBehavior.Status.CLOSED -> quoteDescription.clearFocus()
            }
        }
    }

    private fun updateUI() {
        binding.quoteDescription.setText(viewModel.quoteDescription)
        binding.quoteTopicIcon.setImageResource(viewModel.topicIconId.getResIdTopicIcon())
        binding.quoteTopicTitle.setTextOrUndefined(viewModel.topicTitle)
        binding.quoteTopicSubtitle.setTextOrGone(viewModel.topicSubtitle)
    }

    override fun onStop() {
        super.onStop()
        val description = binding.quoteDescription.text.toString()
        viewModel.save(description)
    }

    private fun navToTopic() {
        val direction = QuoteFragmentDirections.actionQuoteToTopicParent(
            topicParentId = viewModel.topicId
        )
        findNavController().navigate(direction)
    }
}