package ru.sergeyzabelin.mylearning.ui.dictionary

import android.os.Bundle
import android.view.*
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.sergeyzabelin.mylearning.R
import ru.sergeyzabelin.mylearning.databinding.FragmentDictionaryTopicEditBinding
import ru.sergeyzabelin.mylearning.utils.autoCleared


@AndroidEntryPoint
class DictionaryTopicEditFragment : Fragment() {

    private val viewModel by viewModels<DictionaryTopicEditViewModel>()
    private var binding by autoCleared<FragmentDictionaryTopicEditBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dataBinding = FragmentDictionaryTopicEditBinding.inflate(inflater, container, false)

        dataBinding.setLifecycleOwner { lifecycle }
        dataBinding.topic = viewModel.topic

        binding = dataBinding
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.titleLayout.editText?.addTextChangedListener {
            viewModel.checkInputTitle(it.toString())
            binding.titleLayout.error = getStatusError(viewModel.inputTitleStatus)
            binding.titleLayout.helperText = getStatusHelper(viewModel.inputTitleStatus)
            unlockFab()
        }

        binding.labelLayout.editText?.addTextChangedListener {
            viewModel.checkInputLabel(it.toString())
        }

        binding.fab.setOnClickListener {
            binding.titleLayout.isEnabled = false
            binding.labelLayout.isEnabled = false
            viewModel.save()
            findNavController().popBackStack()
        }
    }

    private fun getStatusError(status: DictionaryInputStatus): String? {
        return when (status) {
            DictionaryInputStatus.ERROR_EMPTY -> this.resources.getString(R.string.message_error_empty)
            DictionaryInputStatus.SUCCESS -> null
            else -> null
        }
    }

    private fun getStatusHelper(status: DictionaryInputStatus): String? {
        return when (status) {
            DictionaryInputStatus.HELPER_EQUAL -> this.resources.getString(R.string.message_helper_equal)
            DictionaryInputStatus.SUCCESS -> null
            else -> null
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_app_bar, menu)
    }

    private fun unlockFab() {
        binding.fab.visibility = if (viewModel.isAllInputCorrect()) View.VISIBLE else View.GONE
    }
}