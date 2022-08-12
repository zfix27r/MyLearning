package ru.zfix27r.domain.model

import ru.zfix27r.domain.model.common.ResponseType

sealed class DictionaryResModel {
    data class Data(
        val topic: TopicMain,
        val topics: List<TopicSub>
    ) : DictionaryResModel() {
        data class TopicMain(
            val title: String,
            val subTitle: String
        )

        data class TopicSub(
            val id: Long,
            val parentId: Long,
            val title: String,
            val subTitle: String,
            val difficulty: Int
        )
    }

    data class Fail(val errorType: ResponseType) : DictionaryResModel()
}