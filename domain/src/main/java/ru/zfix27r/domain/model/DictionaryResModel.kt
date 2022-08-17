package ru.zfix27r.domain.model

import ru.zfix27r.domain.model.common.ResponseType

sealed class DictionaryResModel

data class DictionaryDataModel(
    val topic: Topic,
    val topics: List<Topics>
) : DictionaryResModel() {
    data class Topic(
        val title: String,
        val subTitle: String
    )

    data class Topics(
        val id: Long,
        val parentId: Long,
        val title: String,
        val subTitle: String,
        val difficulty: Int
    )
}

data class DictionaryFailModel(val errorType: ResponseType) : DictionaryResModel()