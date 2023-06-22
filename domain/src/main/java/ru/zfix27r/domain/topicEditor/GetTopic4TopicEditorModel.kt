package ru.zfix27r.domain.topicEditor

data class GetTopic4TopicEditorModel(
    val id: Int,
    val parentId: Int?,
    val parentTitle: String?,
    val parentSubtitle: String?,
    val title: String,
    val subtitle: String?,
    val difficulty: Int?,
)