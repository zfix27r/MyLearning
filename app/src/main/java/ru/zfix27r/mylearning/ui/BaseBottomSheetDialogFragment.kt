package ru.zfix27r.mylearning.ui

import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

open class BaseBottomSheetDialogFragment(layoutId: Int) : BottomSheetDialogFragment(layoutId) {
    private val mainActivity by lazy { requireActivity() as MainActivity }
    private lateinit var baseViewModel: BaseViewModel

    protected val prevStackSavedState by lazy { findNavController().previousBackStackEntry!!.savedStateHandle }

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

    fun navPopBack() {
        findNavController().popBackStack()
    }
}