package ru.zfix27r.mylearning.ui

import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.textview.MaterialTextView
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

val topicIcons = listOf(
    0 to R.drawable.ic_topic_activity,
    1 to R.drawable.ic_topic_android,
    2 to R.drawable.ic_topic_java,
    3 to R.drawable.ic_topic_kotlin,
    4 to R.drawable.ic_topic_code,
    5 to R.drawable.ic_topic_json,
    6 to R.drawable.ic_topic_intellij_idea,
)

fun Int?.getResIdTopicIcon(): Int = topicIcons[this ?: 0].second

fun Int?.getMoreThanZeroOrNull() = if (this == null || this == 0) null else this
fun String?.getNotEmptyOrNull() = if (this.isNullOrEmpty()) null else this