package ru.zfix27r.data.model.db

import androidx.room.Embedded
import androidx.room.Relation
import ru.zfix27r.domain.model.DictionaryDataModel
import ru.zfix27r.domain.model.DictionaryResModel

data class DictionaryDb(
    @Embedded
    val topic: Topic,

    @Relation(
        entity = TopicDbEntity::class,
        parentColumn = TopicDbEntity.ID,
        entityColumn = TopicDbEntity.PARENT_ID
    )
    val topics: List<TopicDbEntity>
) {
    data class Topic(
        val id: Long,
        val title: String,
        val subtitle: String,
    )

    fun toDictionary(): DictionaryResModel {
        val topic = DictionaryDataModel.TopicMain(
            title = this.topic.title,
            subTitle = this.topic.subtitle
        )

        val topics = this.topics.map {
            DictionaryDataModel.TopicSub(
                id = it.id,
                parentId = it.parentId,
                title = it.title,
                subTitle = it.subtitle,
                difficulty = it.difficulty
            )
        }

        return DictionaryDataModel(topic = topic, topics = topics)
    }
}