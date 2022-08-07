package ru.zfix27r.domain.model.topic

sealed class TopicResModel {
    data class Success(
        val title: String,
        val subTitle: String
    ) : TopicResModel()

    data class Fail(val errorType: Int) : TopicResModel()
}