package ru.zfix27r.domain.model.topic

data class TopicModel(
    val id: Long,
    val parentTopicId: Long,
    val title: String,
    val subTitle: String,
    val difficulty: Int // 0-10
)
