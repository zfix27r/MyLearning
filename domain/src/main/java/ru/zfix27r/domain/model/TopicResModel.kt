package ru.zfix27r.domain.model

import ru.zfix27r.domain.model.common.ResponseType

sealed class TopicResModel {
    data class Data(
        val title: String,
        val subtitle: String
    ) : TopicResModel()

    data class Fail(val errorType: ResponseType) : TopicResModel()
}