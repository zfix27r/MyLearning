package ru.zfix27r.domain.model

data class AddTopicReqModel(
    val id: Long,
    val parentId: Long,
    val title: String,
    val subTitle: String,
    val difficulty: Int
)