package ru.zfix27r.mylearning.ui.activity

import android.view.MenuItem
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import com.google.android.material.search.SearchBar
import ru.zfix27r.mylearning.R

class Searchbar(
    private val toolbar: SearchBar,
) {
    val height = toolbar.minimumHeight
    var statusBarHeight = 0
    var isDefaultScrollFlagsEnabled
        get() = toolbar.isDefaultScrollFlagsEnabled
        set(value) {
            toolbar.isDefaultScrollFlagsEnabled = value
        }

    init {
        ViewCompat.setOnApplyWindowInsetsListener(toolbar) { view, insets ->
            val insetsStatusBar = insets.getInsets(WindowInsetsCompat.Type.statusBars())
            statusBarHeight = insetsStatusBar.top
            view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = insetsStatusBar.top
            }
            WindowInsetsCompat.CONSUMED
        }
    }

    fun updateMenu(menuId: Int, menuListener: ((MenuItem) -> Boolean)? = null) {
        toolbar.menu.clear()
        toolbar.inflateMenu(menuId)
        toolbar.setOnMenuItemClickListener(menuListener)
    }

    fun updateToolbarHome(destinationId: Int) {
        when (destinationId) {
            R.id.main -> toolbar.setNavigationIcon(R.drawable.ic_ui_profile)
            else -> toolbar.setNavigationIcon(R.drawable.ic_ui_arrow_back)
        }
    }

    fun updateToolbar(title: String = "", subtitle: String = "") {
        toolbar.title = title
        toolbar.subtitle = subtitle
    }
}