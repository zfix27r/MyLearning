package ru.zfix27r.mylearning.ui.editor.topic.icon

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.zfix27r.mylearning.R
import ru.zfix27r.mylearning.databinding.FragmentTopicEditorIconBinding
import ru.zfix27r.mylearning.ui.BaseBottomSheetDialogFragment
import ru.zfix27r.mylearning.ui.editor.topic.TopicEditorFragment

class TopicEditorIconFragment : BaseBottomSheetDialogFragment(R.layout.fragment_topic_editor_icon) {
    private val binding by viewBinding(FragmentTopicEditorIconBinding::bind)
    private val viewModel by viewModels<TopicEditorIconViewModel>()

    private val adapter = TopicEditorIconAdapter {
        object : TopicEditorIconAdapterCallback {
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
        binding.topicEditorIcon.adapter = adapter
        adapter.submitList(viewModel.icons)
    }

    private fun observeViewModel() {
        viewModel.attachViewModel()
    }

    private fun finish(iconId: Int) {
        prevStackSavedState.set(TopicEditorFragment.TOPIC_ICON_ID, iconId)
        navPopBack()
    }
}