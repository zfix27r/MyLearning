package ru.zfix27r.domain.main

data class GetTopicsByParentIdModel(
    val id: Int,
    val title: String,
    val subtitle: String?,
    val difficulty: Int?,
)