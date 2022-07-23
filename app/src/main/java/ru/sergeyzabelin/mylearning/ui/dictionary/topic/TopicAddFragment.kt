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
import ru.sergeyzabelin.mylearning.databinding.FragmentTopicAddBinding
import ru.sergeyzabelin.mylearning.ui.dictionary.common.InputStatus
import ru.sergeyzabelin.mylearning.utils.autoCleared


@AndroidEntryPoint
class TopicAddFragment : Fragment() {

    private val viewModel by viewModels<TopicAddViewModel>()
    private var binding by autoCleared<FragmentTopicAddBinding>()

    private lateinit var menuDone: MenuItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dataBinding = FragmentTopicAddBinding.inflate(inflater, container, false)
        binding = dataBinding
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        actionBar()

        binding.titleLayout.editText?.addTextChangedListener {
            viewModel.title = it.toString()
            updateUITitleStatus()
            updateUIMenuDone()
        }

        binding.subTitleLayout.editText?.addTextChangedListener {
            viewModel.subTitle = it.toString()
            updateUISubTitleStatus()
            updateUIMenuDone()
        }
    }

    private fun actionBar() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.topic_app_bar, menu)
                menuDone = menu.findItem(R.id.done)
                menuDone.isVisible = true
                lockMenuDone()
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

    private fun updateUITitleStatus() {
        binding.titleLayout.let {
            when (viewModel.inputTitleStatus) {
                InputStatus.EMPTY -> it.error = getString(R.string.error_empty)
                InputStatus.NOT_CHANGED -> it.helperText = getString(R.string.helper_not_changed)
                else -> {
                    it.error = null
                    it.helperText = null
                }
            }
        }
    }

    private fun updateUISubTitleStatus() {
        binding.subTitleLayout.let {
            when (viewModel.inputSubTitleStatus) {
                InputStatus.EMPTY -> it.error = getString(R.string.error_empty)
                InputStatus.NOT_CHANGED -> it.helperText = getString(R.string.helper_not_changed)
                else -> {
                    it.error = null
                    it.helperText = null
                }
            }
        }
    }

    private fun updateUIMenuDone() {
        if (viewModel.isInputCorrectly())
            unlockMenuDone()
        else
            lockMenuDone()
    }

    private fun done() {
        lockMenuDone()
        lockInput()
        viewModel.add() // TODO loading, toast
        //findNavController().popBackStack()
    }

    private fun lockInput() {
        binding.titleLayout.isEnabled = false
        binding.subTitleLayout.isEnabled = false
    }

    private fun unlockInput() {
        binding.titleLayout.isEnabled = true
        binding.subTitleLayout.isEnabled = true
    }

    private fun lockMenuDone() {
        menuDone.isEnabled = false
    }

    private fun unlockMenuDone() {
        menuDone.isEnabled = true
    }
}