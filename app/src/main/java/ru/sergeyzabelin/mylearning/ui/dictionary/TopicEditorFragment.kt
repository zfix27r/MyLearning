package ru.sergeyzabelin.mylearning.ui.dictionary

import android.os.Bundle
import android.text.Editable
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.sergeyzabelin.mylearning.R
import ru.sergeyzabelin.mylearning.databinding.FragmentTopicEditorBinding
import ru.sergeyzabelin.mylearning.ui.dictionary.common.InputStatus
import ru.sergeyzabelin.mylearning.utils.autoCleared
import ru.zfix27r.domain.model.TopicResModel
import ru.zfix27r.domain.model.common.ErrorType

@AndroidEntryPoint
class TopicEditorFragment : Fragment() {

    private val viewModel by viewModels<TopicEditorViewModel>()
    private var binding by autoCleared<FragmentTopicEditorBinding>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dataBinding = FragmentTopicEditorBinding.inflate(inflater, container, false)
        dataBinding.lifecycleOwner = viewLifecycleOwner
        dataBinding.viewModel = viewModel
        actionBar()

        if (viewModel.isSaveMode()) loadTopic()

        binding = dataBinding
        return dataBinding.root
    }

    private fun actionBar() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.topic_app_bar, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.done -> onClickDone()
                    android.R.id.home -> findNavController().popBackStack()
                }

                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun onClickDone() {
        if (viewModel.isInputConditionsCorrectly()) {
            binding.titleLayout.isEnabled = false
            binding.subTitleLayout.isEnabled = false

            lifecycleScope.launch {
                viewModel.save().collect {
                    if (it == null) findNavController().popBackStack()
                    when (it?.errorType) {
                        ErrorType.UNKNOWN_ERROR -> viewSnackBar(getString(R.string.error_unknown))
                        else -> {}
                    }
                }
            }
        }

        binding.titleLayout.isEnabled = true
        binding.subTitleLayout.isEnabled = true
    }

    private fun viewSnackBar(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.titleLayout.editText?.addTextChangedListener { onTitleChanged(it) }
        binding.subTitleLayout.editText?.addTextChangedListener { onSubTitleChanged(it) }
    }

    private fun loadTopic() {
        lifecycleScope.launch {
            viewModel.getTopic().collect {
                when (it) {
                    // TODO #5 При загрузке с сети добавить лоадинг, записывать ошибку в какой то логгер
                    is TopicResModel.Success -> {
                        viewModel.topic = it
                        binding.title.setText(it.title)
                        binding.subTitle.setText(it.subTitle)
                    }
                    is TopicResModel.Fail -> {
                        viewSnackBar(getString(R.string.error_inner))
                        findNavController().popBackStack()
                    }
                }
            }
        }
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
