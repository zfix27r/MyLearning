package ru.zfix27r.domain.model.topic

data class AddTopicModel(
    val parentTopicId: Long,
    val title: String,
    val subTitle: String,
    val difficulty: Int
)