package ru.zfix27r.mylearning.ui.editor.quote

import ru.zfix27r.mylearning.ui.editor.EMPTY

data class QuoteEditorSaveModel(
    val id: Int?,

    var description: String = "",

    var topicId: Int = 0,
    var topicTitle: String = "",
    var topicSubtitle: String = "",

    var statusDescription: Int = EMPTY,
)