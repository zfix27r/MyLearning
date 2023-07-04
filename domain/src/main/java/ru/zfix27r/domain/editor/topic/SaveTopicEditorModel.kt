package ru.zfix27r.domain.editor.topic

data class SaveTopicEditorModel(
    val id: Int?,
    val parentId: Int?,
    val iconId: Int?,
    val title: String,
    val subtitle: String?,
)