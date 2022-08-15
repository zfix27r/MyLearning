package ru.zfix27r.domain.model.content

sealed class ContentResModel {
    data class Success(
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

        data class Question(
            val id: Long,
            val text: String
        )
    }

    data class Fail(val errorType: Int) : ContentResModel()
}