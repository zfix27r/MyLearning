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
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import ru.sergeyzabelin.mylearning.R
import ru.sergeyzabelin.mylearning.databinding.FragmentTopicEditorBinding
import ru.sergeyzabelin.mylearning.ui.dictionary.common.InputStatus
import ru.sergeyzabelin.mylearning.utils.autoCleared

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
                    R.id.done -> done()
                    android.R.id.home -> findNavController().popBackStack()
                }

                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.titleLayout.editText?.addTextChangedListener { onTitleChanged(it) }
        binding.subTitleLayout.editText?.addTextChangedListener { onSubTitleChanged(it) }
    }

    private fun onTitleChanged(editText: Editable?) {
        viewModel.title = editText.toString()
        updateInputStatus(binding.titleLayout, viewModel.inputTitleStatus)
    }

    private fun onSubTitleChanged(editText: Editable?) {
        viewModel.subTitle = editText.toString()
        updateInputStatus(binding.subTitleLayout, viewModel.inputSubTitleStatus)
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

    private fun done() {
        if (viewModel.isInputCorrectly()) {
            binding.titleLayout.isEnabled = false
            binding.subTitleLayout.isEnabled = false

            viewModel.save()
        }

        binding.titleLayout.isEnabled = true
        binding.subTitleLayout.isEnabled = true
    }
}