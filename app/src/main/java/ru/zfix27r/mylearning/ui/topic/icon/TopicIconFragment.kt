package ru.zfix27r.mylearning.ui.topic.icon

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.zfix27r.mylearning.R
import ru.zfix27r.mylearning.databinding.FragmentTopicIconBinding
import ru.zfix27r.mylearning.ui.BaseBottomSheetDialogFragment
import ru.zfix27r.mylearning.ui.topic.TopicFragment

class TopicIconFragment : BaseBottomSheetDialogFragment(R.layout.fragment_topic_icon) {
    private val binding by viewBinding(FragmentTopicIconBinding::bind)
    private val viewModel by viewModels<TopicIconViewModel>()

    private val adapter = TopicIconAdapter {
        object : TopicIconAdapterCallback {
            override fun onClick(iconId: Int) {
                finish(iconId)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        setView()
    }

    private fun setView() {
        binding.topicIcon.adapter = adapter
        adapter.submitList(viewModel.icons)
    }

    private fun observeViewModel() {
        viewModel.attachToBaseViewModel()
    }

    private fun finish(iconId: Int) {
        prevStackSavedState.set(TopicFragment.TOPIC_ICON_ID, iconId)
        navPopBack()
    }
}