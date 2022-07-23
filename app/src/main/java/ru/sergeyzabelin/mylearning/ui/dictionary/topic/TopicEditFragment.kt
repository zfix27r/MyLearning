package ru.sergeyzabelin.mylearning.ui.dictionary.topic

import android.os.Bundle
import android.view.*
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.sergeyzabelin.mylearning.R
import ru.sergeyzabelin.mylearning.databinding.FragmentTopicEditBinding
import ru.sergeyzabelin.mylearning.ui.dictionary.common.InputStatus
import ru.sergeyzabelin.mylearning.utils.autoCleared


@AndroidEntryPoint
class TopicEditFragment : Fragment() {

    private val viewModel by viewModels<TopicEditViewModel>()
    private var binding by autoCleared<FragmentTopicEditBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dataBinding = FragmentTopicEditBinding.inflate(inflater, container, false)

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
            viewModel.checkInputSubTitle(it.toString())
        }

        binding.fab.setOnClickListener {
            binding.titleLayout.isEnabled = false
            binding.labelLayout.isEnabled = false
            viewModel.save()
            findNavController().popBackStack()
        }
    }

    private fun getStatusError(status: InputStatus): String? {
        return when (status) {
            InputStatus.EMPTY -> this.resources.getString(R.string.error_empty)
            InputStatus.SUCCESS -> null
            else -> null
        }
    }

    private fun getStatusHelper(status: InputStatus): String? {
        return when (status) {
            InputStatus.NOT_CHANGED -> this.resources.getString(R.string.helper_not_changed)
            InputStatus.SUCCESS -> null
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