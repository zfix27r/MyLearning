package ru.zfix27r.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Relation
import ru.zfix27r.data.local.entity.TopicEntity
import ru.zfix27r.domain.model.GetDictionaryResModel

data class DictionaryDb(
    @Embedded
    val topic: TopicEntity,

    @Relation(
        entity = TopicEntity::class,
        parentColumn = TopicEntity.ID,
        entityColumn = TopicEntity.PARENT_ID,
    )
    val topics: List<TopicEntity>
) {
/*    data class TopicMain(
        @ColumnInfo(name = TopicEntity.TITLE)
        val title: String,
        @ColumnInfo(name = TopicEntity.SUBTITLE)
        val subtitle: String?,
    )

    data class TopicItem(
        @ColumnInfo(name = TopicEntity.ID)
        val id: Long,
        @ColumnInfo(name = TopicEntity.PARENT_ID)
        val parentId: Long,
        @ColumnInfo(name = TopicEntity.TITLE)
        val title: String,
        @ColumnInfo(name = TopicEntity.SUBTITLE)
        val subtitle: String?,
        @ColumnInfo(name = TopicEntity.DIFFICULTY)
        val difficulty: Int?,
    )

    fun toGetDictionaryResModel() = GetDictionaryResModel(
        topic = topic.toTopicGetDictionaryResModel(),
        topics = topics.toTopicsGetDictionaryResModel(),
    )

    private fun TopicMain.toTopicGetDictionaryResModel() = GetDictionaryResModel.Topic(
        title = this.title,
        subTitle = this.subtitle,
    )

    private fun List<TopicItem>.toTopicsGetDictionaryResModel() = this.map {
        GetDictionaryResModel.Topics(
            id = it.id,
            parentId = it.parentId,
            title = it.title,
            subTitle = it.subtitle,
            difficulty = it.difficulty
        )
    }*/
}