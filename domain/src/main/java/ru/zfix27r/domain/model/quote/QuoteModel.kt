package ru.zfix27r.domain.model.quote

data class QuoteModel(
    val id: Long,
    val text: String,
    val sourceId: Long,
    val usability: Int // 0-10
)
