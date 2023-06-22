package ru.zfix27r.data.local.model

import androidx.room.ColumnInfo
import ru.zfix27r.data.local.entity.TopicEntity

data class GetTopicDataModel(
    @ColumnInfo(name = TopicEntity.ID)
    val id: Int,

    @ColumnInfo(name = TopicEntity.PARENT_ID)
    val parentId: Int?,

    @ColumnInfo(name = PARENT_TOPIC_TITLE)
    val parentTitle: String?,

    @ColumnInfo(name = PARENT_TOPIC_SUBTITLE)
    val parentSubtitle: String?,

    @ColumnInfo(name = TopicEntity.TITLE)
    val title: String,

    @ColumnInfo(name = TopicEntity.SUBTITLE)
    val subtitle: String?,

    @ColumnInfo(name = TopicEntity.DIFFICULTY)
    val difficulty: Int?,
) {
    companion object {
        const val PARENT_TOPIC_TITLE = "parent_topic_title"
        const val PARENT_TOPIC_SUBTITLE = "parent_topic_subtitle"
    }
}
