package ru.zfix27r.mylearning.ui.topic

import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import ru.zfix27r.mylearning.R
import ru.zfix27r.mylearning.databinding.FragmentTopicEditorBinding
import ru.zfix27r.mylearning.ui.MainActivity
import ru.zfix27r.mylearning.ui.ToolbarMode
import ru.zfix27r.mylearning.ui.UIResult

@AndroidEntryPoint
class TopicEditorFragment : Fragment(R.layout.fragment_topic_editor) {
    private val binding by viewBinding(FragmentTopicEditorBinding::bind)
    private val viewModel by viewModels<TopicEditorViewModel>()
    private val args by navArgs<TopicEditorFragmentArgs>()
    private val mainActivity by lazy {
        requireActivity() as? MainActivity ?: throw ExceptionInInitializerError()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loading(args.topicId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onNewParentOnBackStack()

        prepareToolbar()
        prepareView()

        observersUI()
    }

    private fun onNewParentOnBackStack() {
        findNavController().currentBackStackEntry!!.savedStateHandle.let { saved ->
            saved.get<Boolean>(NEW_TOPIC_PARENT)?.let {
                saved.set(NEW_TOPIC_PARENT, null)

                val id = saved.get<Int>(NEW_TOPIC_PARENT_ID)
                val title = saved.get<String>(NEW_TOPIC_PARENT_TITLE)
                val subtitle = saved.get<String>(NEW_TOPIC_PARENT_SUBTITLE)
                viewModel.setTopicParent(id, title, subtitle)
            }
        }
    }

    private fun prepareToolbar() {
        mainActivity.toolbar(ToolbarMode.EDIT) {
            println("@@@@@@@@@@@@ CLICK 1")
            when (it.id) {
                R.id.toolbar_done -> onClickToolbarDone()
            }
        }
//        mainActivity.toolbarHome()
    }

    private fun prepareView() {
        binding.topicEditorTitle.editText?.addTextChangedListener { it.updateTitle() }
        binding.topicEditorSubtitle.editText?.addTextChangedListener { it.updateSubtitle() }
        binding.topicEditorParentTopic.setOnClickListener { navigateToTopicEditorParent() }
    }

    private fun observersUI() {
        viewModel.isUpdate.observe(viewLifecycleOwner) { updateUI() }

        viewModel.isDone.observe(viewLifecycleOwner) {
            if (it) findNavController().popBackStack() else enableUI()
        }
    }

    private fun onClickToolbarDone() {
        println("!!!!!!!!!!!!!!! 1")
        disableUI()
        viewModel.done()
    }

    private fun updateUI() {
        binding.topicEditorTitleText.setText(viewModel.topicTitle)
        binding.topicEditorSubtitleText.setText(viewModel.topicSubtitle)

        viewModel.topicParentTitle?.let {
            binding.topicEditorParentTopicTitle.text = it
        } ?: run {
            binding.topicEditorParentTopicTitle.setText(R.string.topic_editor_parent_id_null)
        }

        binding.topicEditorParentTopicSubtitle.text = viewModel.topicParentSubtitle
    }

    private fun disableUI() {
        changeUI(false)
    }

    private fun enableUI() {
        changeUI(true)
    }

    private fun changeUI(state: Boolean) {
        binding.topicEditorTitle.isEnabled = state
        binding.topicEditorSubtitle.isEnabled = state
        binding.topicEditorParentTopic.isEnabled = state
    }

    private fun navigateToTopicEditorParent() {
        val direction = TopicEditorFragmentDirections
            .actionTopicEditorToTopicEditorParent(
                topicId = viewModel.topicParentId,
                topicIdSelf = viewModel.topicId,
                topicIdChecked = viewModel.topicParentId
            )
        findNavController().navigate(direction)
    }

    private fun Editable?.updateTitle() {
        this?.let {
            viewModel.run {
                it.toString()
                    .setTitle()
                    .updateTextInputLayout(binding.topicEditorTitle)
            }
        }
    }

    private fun Editable?.updateSubtitle() {
        this?.let {
            viewModel.run {
                it.toString()
                    .setSubtitle()
                    .updateTextInputLayout(binding.topicEditorSubtitle)
            }
        }
    }

    private fun UIResult.updateTextInputLayout(layout: TextInputLayout) {
        layout.error = when (this) {
            UIResult.FIELD_EMPTY -> getString(R.string.ui_result_field_empty)
            else -> null
        }
    }

    companion object {
        const val NEW_TOPIC_PARENT = "new_topic_parent"
        const val NEW_TOPIC_PARENT_ID = "new_parent_topic_id"
        const val NEW_TOPIC_PARENT_TITLE = "new_parent_topic_title"
        const val NEW_TOPIC_PARENT_SUBTITLE = "new_parent_topic_subtitle"
    }
}