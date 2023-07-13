package ru.zfix27r.mylearning.ui.base

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class BaseItemDecoration : RecyclerView.ItemDecoration() {
    var topMarginFirstItem = 0

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (position == 0) outRect.top = topMarginFirstItem
    }
}