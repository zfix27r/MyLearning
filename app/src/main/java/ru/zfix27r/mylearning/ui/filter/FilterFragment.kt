package ru.zfix27r.mylearning.ui.filter

import android.os.Bundle
import android.view.View
import ru.zfix27r.mylearning.R
import ru.zfix27r.mylearning.ui.base.BaseFragment

class FilterFragment : BaseFragment(R.layout.fragment_filter) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
    }

    private fun setToolbar() {
        disableScrollTopAppBar()
        searchbar.updateMenu(R.menu.toolbar_empty)
    }
}