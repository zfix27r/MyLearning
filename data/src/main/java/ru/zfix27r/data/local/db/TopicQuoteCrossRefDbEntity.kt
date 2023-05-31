package ru.zfix27r.data.local.db

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import ru.zfix27r.data.local.entity.QuoteEntity
import ru.zfix27r.data.local.entity.TopicEntity
import ru.zfix27r.data.local.db.TopicQuoteCrossRefDbEntity.Companion.QUOTE_ID
import ru.zfix27r.data.local.db.TopicQuoteCrossRefDbEntity.Companion.TABLE_NAME
import ru.zfix27r.data.local.db.TopicQuoteCrossRefDbEntity.Companion.TOPIC_ID

@Entity(
    tableName = TABLE_NAME,
    primaryKeys = [TOPIC_ID, QUOTE_ID],
    indices = [Index(value = [QUOTE_ID])],
    foreignKeys = [
        ForeignKey(
            entity = TopicEntity::class,
            parentColumns = [TopicEntity.ID],
            childColumns = [TOPIC_ID],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = QuoteEntity::class,
            parentColumns = [QuoteEntity.ID_NAME],
            childColumns = [QUOTE_ID],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class TopicQuoteCrossRefDbEntity(
    @NonNull @ColumnInfo(name = TOPIC_ID)
    val topicId: Long,
    @NonNull @ColumnInfo(name = QUOTE_ID)
    val quoteId: Long
) {
    companion object {
        const val TABLE_NAME = "topic_quote_cross_ref"
        const val TOPIC_ID = "topicId"
        const val QUOTE_ID = "quoteId"
    }
}
