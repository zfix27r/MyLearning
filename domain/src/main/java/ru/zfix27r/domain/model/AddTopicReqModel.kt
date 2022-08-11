package ru.zfix27r.domain.model

data class AddTopicReqModel(
    val parentTopicId: Long,
    val title: String,
    val subTitle: String,
    val difficulty: Int
)