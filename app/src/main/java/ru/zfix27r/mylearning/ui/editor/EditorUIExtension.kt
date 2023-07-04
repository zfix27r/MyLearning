package ru.zfix27r.mylearning.ui.editor

import com.google.android.material.textfield.TextInputLayout
import ru.zfix27r.mylearning.R

const val OK = 0
const val EMPTY = 1

fun TextInputLayout.updateTextInputLayout(check: Int) {
    when (check) {
        EMPTY -> this.error = this.context.getString(R.string.ui_result_field_empty)
    }
}
