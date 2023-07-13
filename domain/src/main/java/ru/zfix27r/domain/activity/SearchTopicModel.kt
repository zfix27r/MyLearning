package ru.zfix27r.domain.activity

data class SearchTopicModel(
    val id: Int,
    val iconId: Int?,
    val title: String,
    val subtitle: String?,
)