package ru.zfix27r.mylearning.ui

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.ActionMode
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


open class BaseFragment(layoutId: Int) : Fragment(layoutId) {
    private val mainActivity by lazy { requireActivity() as MainActivity }
    private lateinit var baseViewModel: BaseViewModel

    private var actionMode: ActionMode? = null
    protected val currentStackSavedState by lazy { findNavController().currentBackStackEntry!!.savedStateHandle }
    protected val toolbar by lazy { mainActivity.toolbar }

    fun enableScrollTopAppBar() {
        toolbar.isDefaultScrollFlagsEnabled = true
    }

    fun disableScrollTopAppBar() {
        mainActivity.binding.topAppBar.setExpanded(true, true)
        toolbar.isDefaultScrollFlagsEnabled = false
    }

    fun BaseViewModel.attachViewModel() {
        baseViewModel = this
        baseViewModel.event.observe(viewLifecycleOwner) {
            when (it) {
                is BaseViewModelEvent.Loading -> mainActivity.viewLoading(it.state)
                is BaseViewModelEvent.Error -> mainActivity.viewError(it.messageId)
                else -> {}
            }
        }
    }

    override fun onPause() {
        super.onPause()
        actionMode?.finish()
    }

    fun View.setInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
            val insetsStatusBar = insets.getInsets(WindowInsetsCompat.Type.statusBars())
            view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = toolbar.height + insetsStatusBar.top
            }
            WindowInsetsCompat.CONSUMED
        }
    }

    fun viewActionMode(callback: () -> ActionModeCallback) {
        actionMode = mainActivity.startSupportActionMode(callback.invoke())
    }

    fun navPopBack() {
        findNavController().popBackStack()
    }
}