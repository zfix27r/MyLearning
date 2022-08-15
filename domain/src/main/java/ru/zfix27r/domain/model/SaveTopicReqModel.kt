package ru.zfix27r.domain.model

data class SaveTopicReqModel(
    val id: Long,
    val title: String,
    val subtitle: String
)