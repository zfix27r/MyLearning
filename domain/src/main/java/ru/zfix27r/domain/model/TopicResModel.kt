package ru.zfix27r.domain.model

import ru.zfix27r.domain.model.common.ErrorType

sealed class TopicResModel {
    data class Success(
        val title: String,
        val subTitle: String
    ) : TopicResModel()

    data class Fail(val errorType: ErrorType) : TopicResModel()
}