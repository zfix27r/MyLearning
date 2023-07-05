package ru.zfix27r.data.local.model

import androidx.room.ColumnInfo
import ru.zfix27r.data.local.entity.QuoteEntity
import ru.zfix27r.data.local.entity.TopicEntity

data class GetMainDataModel(
    @ColumnInfo(name = QUOTE_ID)
    val id: Int,
    @ColumnInfo(name = QUOTE_DESCRIPTION)
    val description: String,

    @ColumnInfo(name = TOPIC_ID)
    val topicId: Int?,
    @ColumnInfo(name = TOPIC_ICON_ID)
    val topicIconId: Int?,
    @ColumnInfo(name = TOPIC_TITLE)
    val topicTitle: String?,
    @ColumnInfo(name = TOPIC_SUBTITLE)
    val topicSubtitle: String?,
) {
    companion object {
        const val QUOTE_ID = "${QuoteEntity.TABLE_QUOTE}_${QuoteEntity.ID}"
        const val QUOTE_DESCRIPTION = "${QuoteEntity.TABLE_QUOTE}_${QuoteEntity.DESCRIPTION}"

        const val TOPIC_ID = "${TopicEntity.TABLE_TOPIC}_${TopicEntity.ID}"
        const val TOPIC_ICON_ID = "${TopicEntity.TABLE_TOPIC}_${TopicEntity.ICON_ID}"
        const val TOPIC_TITLE = "${TopicEntity.TABLE_TOPIC}_${TopicEntity.TITLE}"
        const val TOPIC_SUBTITLE = "${TopicEntity.TABLE_TOPIC}_${TopicEntity.SUBTITLE}"
    }
}