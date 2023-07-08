package ru.zfix27r.mylearning.ui

import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import ru.zfix27r.data.error.NotFoundError
import ru.zfix27r.mylearning.R

fun Throwable.getErrorStringRes(): Int {
    return when (this) {
        is NotFoundError -> R.string.error_not_found
        else -> throw this
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
    7 to R.drawable.ic_topic_stack,
    8 to R.drawable.ic_topic_sdk,
    9 to R.drawable.ic_topic_atom,
    10 to R.drawable.ic_topic_video,
    11 to R.drawable.ic_topic_bug,
    12 to R.drawable.ic_topic_ios,
    13 to R.drawable.ic_topic_idea,
    14 to R.drawable.ic_topic_github,
    15 to R.drawable.ic_topic_database,
    16 to R.drawable.ic_topic_dashboard,
    17 to R.drawable.ic_topic_controller,
)

fun Int?.getResIdTopicIcon(): Int = topicIcons[this ?: 0].second

fun Int?.getMoreThanZeroOrNull() = if (this == null || this == 0) null else this
fun String?.getNotEmptyOrNull() = if (this.isNullOrEmpty()) null else this

fun View.showKeyboard() {
    val imm =
        this.context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun View.closeKeyboard() {
    val imm =
        this.context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}
