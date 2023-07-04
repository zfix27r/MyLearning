package ru.zfix27r.domain.editor.topic

data class GetTopicEditorModel(
    val iconId: Int?,
    val title: String,
    val subtitle: String?,
    val parentId: Int?,
    val parentTitle: String?,
    val parentSubtitle: String?,
)