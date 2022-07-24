package ru.sergeyzabelin.mylearning.ui.dictionary.topic

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dataBinding = FragmentTopicEditBinding.inflate(inflater, container, false)

        binding = dataBinding
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.topic = viewModel.data

        actionBar()
        title()
        subTitle()
        fab()
    }

    private fun actionBar() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.dictionary_app_bar, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    android.R.id.home -> findNavController().popBackStack()
                }

                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun title() {
        binding.titleLayout.editText?.addTextChangedListener {
            viewModel.checkInputTitle(it.toString())
            binding.titleLayout.error = getStatusError(viewModel.inputTitleStatus)
            binding.titleLayout.helperText = getStatusHelper(viewModel.inputTitleStatus)
            unlockFab()
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

    private fun subTitle() {
        binding.subTitleLayout.editText?.addTextChangedListener {
            viewModel.checkInputSubTitle(it.toString())
        }
    }

    private fun fab() {
        binding.fab.setOnClickListener {
            binding.titleLayout.isEnabled = false
            binding.subTitleLayout.isEnabled = false
            viewModel.save()
            findNavController().popBackStack()
        }
    }

    private fun unlockFab() {
        binding.fab.visibility = if (viewModel.isAllInputCorrect()) View.VISIBLE else View.GONE
    }
}