package ru.zfix27r.mylearning.ui.base

import android.graphics.drawable.InsetDrawable
import android.view.Gravity
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import ru.zfix27r.mylearning.R
import ru.zfix27r.mylearning.ui.activity.MainActivity

open class BaseDialogFragment(layoutId: Int) : DialogFragment(layoutId) {
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

    protected fun setInsets() {
        val backgroundColorDrawable =
            AppCompatResources.getDrawable(requireContext(), R.drawable.background_corner)
        val offset = requireContext().theme.resources.getDimension(R.dimen.offset).toInt()
        val topInset = searchbar.height + offset
        val inset = InsetDrawable(backgroundColorDrawable, offset, topInset, offset, 0)
        dialog!!.window!!.setBackgroundDrawable(inset)
    }

    protected fun setWidth() {
        dialog!!.window!!.attributes.run {
            width = requireContext().resources.displayMetrics.widthPixels
            gravity = Gravity.START or Gravity.TOP
        }
    }
}