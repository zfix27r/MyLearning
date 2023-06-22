package ru.zfix27r.domain.topicEditor

data class GetTopics4TopicEditorParentModel(
    val id: Int,
    val parentId: Int,
    val title: String,
    val subtitle: String?,
)