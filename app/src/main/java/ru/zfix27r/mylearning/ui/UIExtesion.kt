package ru.zfix27r.mylearning.ui

import androidx.fragment.app.Fragment
import ru.zfix27r.mylearning.MainActivity

fun Fragment.setToolbarTitles(title: String, subtitle: String) {
    (activity as MainActivity).supportActionBar?.let {
        it.title = title
        it.subtitle = subtitle
    }
}