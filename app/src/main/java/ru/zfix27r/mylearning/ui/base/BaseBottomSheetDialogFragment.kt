package ru.zfix27r.mylearning.ui.base

import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.zfix27r.mylearning.ui.activity.MainActivity

open class BaseBottomSheetDialogFragment(layoutId: Int) : BottomSheetDialogFragment(layoutId) {
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

    protected val prevStackSavedState by lazy { findNavController().previousBackStackEntry!!.savedStateHandle }
}