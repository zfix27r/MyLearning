package ru.zfix27r.mylearning.ui

import androidx.fragment.app.Fragment
import ru.zfix27r.mylearning.R
import ru.zfix27r.data.error.NotFoundError

fun Throwable.getErrorStringRes(): Int {
    return when (this) {
        is NotFoundError -> R.string.error_not_found
        else -> throw this
    }
}

fun Fragment.setToolbarTitles(title: String, subtitle: String) {
    (activity as MainActivity).supportActionBar?.let {
        it.title = title
        it.subtitle = subtitle
    }
}