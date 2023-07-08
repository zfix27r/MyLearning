package ru.zfix27r.mylearning.ui.main

data class MainAdapterModel(
    val quoteId: Int,
    val quoteDescription: String,

    val topicId: Int?,
    val topicIconId: Int?,
    val topicTitle: String?,
    val topicSubtitle: String?,
)