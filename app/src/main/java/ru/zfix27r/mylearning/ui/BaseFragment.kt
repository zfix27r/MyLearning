package ru.zfix27r.mylearning.ui

import androidx.appcompat.view.ActionMode
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

open class BaseFragment(layoutId: Int) : Fragment(layoutId) {
    private val mainActivity by lazy { requireActivity() as MainActivity }
    private lateinit var baseViewModel: BaseViewModel

    protected var actionMode: ActionMode? = null
    protected val currentStackSavedState by lazy { findNavController().currentBackStackEntry!!.savedStateHandle }
    protected val toolbar by lazy { mainActivity.toolbar }

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

    fun viewActionMode(callback: () -> ActionModeCallback) {
        actionMode = mainActivity.startSupportActionMode(callback.invoke())
    }

    fun navPopBack() {
        findNavController().popBackStack()
    }
}