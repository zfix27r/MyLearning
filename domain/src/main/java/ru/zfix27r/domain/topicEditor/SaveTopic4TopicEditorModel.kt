package ru.zfix27r.domain.topicEditor

data class SaveTopic4TopicEditorModel(
    val id: Int?,
    val parentId: Int?,
    val title: String,
    val subtitle: String?,
    val difficulty: Int?,
)