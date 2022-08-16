package ru.sergeyzabelin.mylearning.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    fun setToolbarTitles(title: String, subtitle: String) {
        (activity as AppCompatActivity).supportActionBar?.let { actionBar ->
            actionBar.title = title
            actionBar.subtitle = subtitle
        }
    }
}