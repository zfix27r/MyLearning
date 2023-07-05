package ru.zfix27r.mylearning.ui.main.more

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.zfix27r.mylearning.R
import ru.zfix27r.mylearning.databinding.FragmentMainMoreBinding
import ru.zfix27r.mylearning.ui.BaseBottomSheetDialogFragment

@AndroidEntryPoint
class MainMoreFragment : BaseBottomSheetDialogFragment(R.layout.fragment_main_more) {
    private val binding by viewBinding(FragmentMainMoreBinding::bind)
    private val viewModel by viewModels<MainMoreViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.attachViewModel()

        setListeners()
    }

    private fun setListeners() {
        binding.mainMenuAddTopic.setOnClickListener { navToTopicEditor() }
        binding.mainMenuAddQuote.setOnClickListener { navToQuoteEditor() }
    }

    private fun navToTopicEditor() {
        findNavController().navigate(R.id.action_main_more_to_topic_editor)
    }

    private fun navToQuoteEditor() {
        findNavController().navigate(R.id.action_main_more_to_quote_editor)
    }
}