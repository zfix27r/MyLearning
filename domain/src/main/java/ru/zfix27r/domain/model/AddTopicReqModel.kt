package ru.zfix27r.domain.model

data class AddTopicReqModel(
    val parentId: Long,
    val title: String,
    val subtitle: String,
    val difficulty: Int
)