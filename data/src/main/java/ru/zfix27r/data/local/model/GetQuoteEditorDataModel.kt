package ru.zfix27r.data.local.model

import androidx.room.ColumnInfo
import ru.zfix27r.data.local.entity.QuoteEntity

data class GetQuoteEditorDataModel(
    @ColumnInfo(name = QuoteEntity.DESCRIPTION)
    val description: String,

    @ColumnInfo(name = TOPIC_ID)
    val topicId: Int?,
    @ColumnInfo(name = TOPIC_TITLE)
    val topicTitle: String?,
    @ColumnInfo(name = TOPIC_SUBTITLE)
    val topicSubtitle: String?,
) {

    companion object {
        const val TOPIC_ID = "topic_id"
        const val TOPIC_TITLE = "topic_title"
        const val TOPIC_SUBTITLE = "topic_subtitle"
    }
}