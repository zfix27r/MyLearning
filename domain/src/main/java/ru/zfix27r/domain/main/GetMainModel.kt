package ru.zfix27r.domain.main

data class GetMainModel(
    val quoteId: Int,
    val quoteDescription: String,

    val topicId: Int?,
    val topicIconId: Int?,
    val topicTitle: String?,
    val topicSubtitle: String?,
)