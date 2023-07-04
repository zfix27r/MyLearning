package ru.zfix27r.data.local.model

import androidx.room.ColumnInfo
import ru.zfix27r.data.local.entity.TopicEntity

data class GetTopicEditorDataModel(
    @ColumnInfo(name = TopicEntity.ICON_ID)
    val iconId: Int?,
    @ColumnInfo(name = TopicEntity.TITLE)
    val title: String,
    @ColumnInfo(name = TopicEntity.SUBTITLE)
    val subtitle: String?,

    @ColumnInfo(name = TOPIC_PARENT_ID)
    val topicParentId: Int?,
    @ColumnInfo(name = TOPIC_PARENT_TITLE)
    val topicParentTitle: String?,
    @ColumnInfo(name = TOPIC_PARENT_SUBTITLE)
    val topicParentSubtitle: String?,
) {
    companion object {
        const val TOPIC_PARENT_ID = "topic_parent_id"
        const val TOPIC_PARENT_TITLE = "topic_parent_title"
        const val TOPIC_PARENT_SUBTITLE = "topic_parent_subtitle"
    }
}