package ru.zfix27r.data.model.db

import androidx.room.Embedded
import androidx.room.Relation
import ru.zfix27r.domain.model.DictionaryResModel

data class DictionaryDb(
    @Embedded
    val topic: Topic,

    @Relation(
        entity = TopicDbEntity::class,
        parentColumn = "id",
        entityColumn = "parentId",
        projection = ["id", "parentId", "title", "subTitle", "difficulty"]
    )
    val topics: List<TopicDbEntity>
) {
    data class Topic(
        val id: Long,
        val title: String,
        val subTitle: String,
    )

    fun toDictionary(): DictionaryResModel {
        val topic = DictionaryResModel.Data.TopicMain(
            title = this.topic.title,
            subTitle = this.topic.subTitle
        )

        val topics = this.topics.map {
            DictionaryResModel.Data.TopicSub(
                id = it.id,
                parentId = it.parentId,
                title = it.title,
                subTitle = it.subTitle,
                difficulty = it.difficulty
            )
        }

        return DictionaryResModel.Data(topic = topic, topics = topics)
    }
}