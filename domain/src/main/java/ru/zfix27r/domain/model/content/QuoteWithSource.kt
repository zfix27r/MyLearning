package ru.zfix27r.domain.model.content

data class QuoteWithSource(
    val quote: Quote,
    val source: Source
) {
    data class Quote(
        val id: Long,
        val text: String,
        val usability: Int
    )

    data class Source(
        val id: Long,
        val title: String,
        val year: Int,
        val url: String
    )
}