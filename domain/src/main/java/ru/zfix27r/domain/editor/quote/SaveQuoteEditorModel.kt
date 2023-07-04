package ru.zfix27r.domain.editor.quote

data class SaveQuoteEditorModel(
    val id: Int?,
    val topicId: Int?,
    val description: String,
)