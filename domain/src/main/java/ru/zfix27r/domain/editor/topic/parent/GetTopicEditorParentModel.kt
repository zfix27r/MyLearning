package ru.zfix27r.domain.editor.topic.parent

data class GetTopicEditorParentModel(
    val id: Int,
    val parentId: Int?,
    val title: String,
    val subtitle: String?,
    val childCount: Int?,
)