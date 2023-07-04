package ru.zfix27r.domain.main

data class GetMainTopicsModel(
    val id: Int,
    val iconId: Int?,
    val title: String,
    val subtitle: String?,
)