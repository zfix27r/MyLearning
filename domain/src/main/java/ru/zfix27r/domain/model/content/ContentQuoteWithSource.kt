package ru.zfix27r.domain.model.content

data class ContentQuoteWithSource(
    val quote: Quote,
    val source: Source
) {
    data class Quote(
        val id: Long,
        val text: String,
        val usability: Int // 0-10
    )

    data class Source(
        val id: Long,
        val title: String,
        val year: Int,
        val url: String
    )
}