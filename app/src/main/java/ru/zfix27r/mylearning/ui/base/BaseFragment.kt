package ru.zfix27r.mylearning.ui.base

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.ActionMode
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.zfix27r.mylearning.ui.activity.MainActivity


open class BaseFragment(layoutId: Int) : Fragment(layoutId) {
    /* База всех фаргментов */

    private val mainActivity by lazy { requireActivity() as MainActivity }
    private lateinit var baseViewModel: BaseViewModel

    protected val searchbar by lazy { mainActivity.searchbar }

    protected fun BaseViewModel.attachToBaseViewModel() {
        baseViewModel = this
        baseViewModel.event.observe(viewLifecycleOwner) {
            when (it) {
                is BaseViewModelEvent.Loading -> mainActivity.viewLoading(it.state)
                is BaseViewModelEvent.Error -> mainActivity.viewError(it.messageId)
                else -> {}
            }
        }
    }

    protected fun navPopBack() {
        findNavController().popBackStack()
    }
    /* База всех фаргментов */

    private var actionMode: ActionMode? = null
    protected val currentStackSavedState by lazy { findNavController().currentBackStackEntry!!.savedStateHandle }
    protected val fab by lazy { mainActivity.binding.fab }

    protected fun enableScrollTopAppBar() {
        searchbar.isDefaultScrollFlagsEnabled = true
    }

    protected fun disableScrollTopAppBar() {
        mainActivity.binding.topAppBar.setExpanded(true, true)
        searchbar.isDefaultScrollFlagsEnabled = false
    }

    override fun onPause() {
        super.onPause()
        actionMode?.finish()
    }

    protected fun View.setInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
            val insetsStatusBar = insets.getInsets(WindowInsetsCompat.Type.statusBars())
            view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = searchbar.height + insetsStatusBar.top
            }
            WindowInsetsCompat.CONSUMED
        }
    }

    protected fun viewActionMode(callback: () -> BaseActionModeCallback) {
        actionMode = mainActivity.startSupportActionMode(callback.invoke())
    }
}