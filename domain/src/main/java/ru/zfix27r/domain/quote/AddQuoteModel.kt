package ru.zfix27r.domain.quote

data class AddQuoteModel(
    val text: String,
    val sourceId: Long,
    val usability: Int
)