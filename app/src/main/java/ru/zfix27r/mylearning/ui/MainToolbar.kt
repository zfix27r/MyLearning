package ru.zfix27r.mylearning.ui

import android.view.MenuItem
import com.google.android.material.search.SearchBar
import ru.zfix27r.mylearning.R

class MainToolbar(
    private val toolbar: SearchBar,
) {
    fun updateMenu(menuId: Int, menuListener: ((MenuItem) -> Boolean)? = null) {
        toolbar.menu.clear()
        toolbar.inflateMenu(menuId)
        toolbar.setOnMenuItemClickListener(menuListener)
    }

    fun updateToolbarHome(destinationId: Int) {
        when (destinationId) {
            R.id.main, R.id.main_menu -> toolbar.setNavigationIcon(R.drawable.ic_ui_search)
            else -> toolbar.setNavigationIcon(R.drawable.ic_ui_arrow_back)
        }
    }

    fun updateToolbar(title: String = "", subtitle: String = "") {
        toolbar.title = title
        toolbar.subtitle = subtitle
    }
}