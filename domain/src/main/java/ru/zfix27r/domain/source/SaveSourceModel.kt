package ru.zfix27r.domain.source

data class SaveSourceModel(
    val id: Long,
    val title: String,
    val year: Int,
    val url: String
)
