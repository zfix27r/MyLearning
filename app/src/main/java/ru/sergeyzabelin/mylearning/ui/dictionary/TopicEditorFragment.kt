package ru.sergeyzabelin.mylearning.ui.dictionary

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.sergeyzabelin.mylearning.R
import ru.sergeyzabelin.mylearning.databinding.FragmentTopicEditorBinding
import ru.sergeyzabelin.mylearning.ui.dictionary.common.InputStatus
import ru.zfix27r.domain.model.TopicResModel
import ru.zfix27r.domain.model.common.ErrorType

@AndroidEntryPoint
class TopicEditorFragment : Fragment() {

    private val viewModel by viewModels<TopicEditorViewModel>()
    private val binding by viewBinding(FragmentTopicEditorBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("topicEditorFragment", "onCreate")
        if (viewModel.isSaveMode()) loadTopic()
    }

    private fun actionBar() {
        Log.e("topicEditorFragment", "actionBar")
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.topic_app_bar, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.done -> onClickDone(menuItem)
                    android.R.id.home -> findNavController().popBackStack()
                }

                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun loadTopic() = viewLifecycleOwner.lifecycleScope.launch {
        Log.e("topicEditorFragment", "loadTopic")
        viewModel.getTopic().collect {
            when (it) {
                // TODO #5 При загрузке с сети добавить лоадинг, записывать ошибку в какой то логгер
                is TopicResModel.Success -> {
                    viewModel.topic = it
                }
                is TopicResModel.Fail -> {
                    notifyMessageOnUI(getString(R.string.error_inner))
                    findNavController().popBackStack()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.e("topicEditorFragment", "onCreateView")
        return inflater.inflate(R.layout.fragment_topic_editor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.e("topicEditorFragment", "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        actionBar()

        binding.titleLayout.editText?.addTextChangedListener { onTitleChanged(it) }
        binding.subTitleLayout.editText?.addTextChangedListener { onSubTitleChanged(it) }
    }

    private fun onClickDone(menuItem: MenuItem) {
        if (viewModel.isInputConditionsCorrectly()) {
            lockUIForEdit(menuItem)

            lifecycleScope.launch {
                if (viewModel.isSaveMode()) {
                    viewModel.save().collect {
                        if (it == null) findNavController().popBackStack()
                        when (it?.errorType) {
                            ErrorType.UNKNOWN_ERROR -> notifyMessageOnUI(getString(R.string.error_unknown))
                            else -> {}
                        }
                    }
                } else {
                    viewModel.add().collect {
                        if (it == null) findNavController().popBackStack()
                        when (it?.errorType) {
                            ErrorType.UNKNOWN_ERROR -> notifyMessageOnUI(getString(R.string.error_unknown))
                            else -> {}
                        }
                    }
                }

                unlockUIForEdit(menuItem)
            }
        }
    }

    private fun lockUIForEdit(menuItem: MenuItem) {
        binding.titleLayout.isEnabled = false
        binding.subTitleLayout.isEnabled = false
        menuItem.isEnabled = false
    }

    private fun unlockUIForEdit(menuItem: MenuItem) {
        binding.titleLayout.isEnabled = true
        binding.subTitleLayout.isEnabled = true
        menuItem.isEnabled = true
    }

    private fun notifyMessageOnUI(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
    }

    private fun onTitleChanged(editText: Editable?) {
        updateInputStatus(binding.titleLayout, viewModel.trySetTitle(editText.toString()))
    }

    private fun onSubTitleChanged(editText: Editable?) {
        updateInputStatus(binding.subTitleLayout, viewModel.trySetSubTitle(editText.toString()))
    }

    private fun updateInputStatus(layout: TextInputLayout, status: InputStatus) {
        when (status) {
            InputStatus.EMPTY -> layout.error = getString(R.string.error_empty)
            InputStatus.NOT_CHANGED -> layout.helperText = getString(R.string.helper_not_changed)
            else -> {
                layout.error = null
                layout.helperText = null
            }
        }
    }
}
