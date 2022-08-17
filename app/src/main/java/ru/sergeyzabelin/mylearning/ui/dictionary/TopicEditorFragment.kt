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
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import ru.sergeyzabelin.mylearning.R
import ru.sergeyzabelin.mylearning.databinding.FragmentTopicEditorBinding
import ru.sergeyzabelin.mylearning.ui.common.InputStatus
import ru.zfix27r.domain.model.common.ResponseType.SUCCESS
import ru.zfix27r.domain.model.common.ResponseType.UNKNOWN_ERROR

@AndroidEntryPoint
class TopicEditorFragment : Fragment() {

    private val viewModel by viewModels<TopicEditorViewModel>()
    private val binding by viewBinding(FragmentTopicEditorBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (viewModel.isSaveMode()) viewModel.loadTopic()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_topic_editor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.titleLayout.editText?.addTextChangedListener { onTitleChanged(it) }
        binding.subTitleLayout.editText?.addTextChangedListener { onSubTitleChanged(it) }

        actionBar()
        observeResponseResult()
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
        if (viewModel.tryDone()) lockUIForEdit()
    }

    private fun lockUIForEdit() {
        binding.titleLayout.isEnabled = false
        binding.subTitleLayout.isEnabled = false
    }

    private fun unlockUIForEdit() {
        binding.titleLayout.isEnabled = true
        binding.subTitleLayout.isEnabled = true
    }

    private fun observeResponseResult() {
        viewModel.response.observe(viewLifecycleOwner) {
            when (it) {
                SUCCESS -> {

                    findNavController().popBackStack()
                }
                UNKNOWN_ERROR -> notifyMessageOnUI(getString(R.string.error_unknown))
                else -> {}
            }
            unlockUIForEdit()
        }
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
