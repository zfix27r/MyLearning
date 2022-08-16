package ru.zfix27r.domain.model.content

import ru.zfix27r.domain.model.common.ResponseType

sealed class ContentResModel

data class ContentDataModel(
    val topic: Topic,
    val quotes: List<QuoteWithSource>,
    val questions: List<Question>
) : ContentResModel() {
    data class Topic(
        val id: Long,
        val title: String,
        val subtitle: String
    )

    data class QuoteWithSource(
        val quote: Quote,
        val source: Source
    ) {
        data class Quote(
            val id: Long,
            val sourceId: Long,
            val text: String,
            val usability: Int
        )

        data class Source(
            val title: String,
            val year: Int,
            val url: String
        )
    }

    data class Question(
        val id: Long,
        val text: String
    )
}

data class ContentFailModel(val responseType: ResponseType) : ContentResModel()