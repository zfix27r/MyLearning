package ru.zfix27r.domain.editor.quote

data class GetQuoteEditorModel(
    val description: String,

    val topicId: Int?,
    val topicIconId: Int?,
    val topicTitle: String?,
    val topicSubtitle: String?,
)