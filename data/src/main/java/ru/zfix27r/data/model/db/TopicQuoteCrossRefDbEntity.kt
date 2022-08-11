package ru.zfix27r.data.model.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "topic_quote_cross_ref",
    primaryKeys = ["topicId", "quoteId"],
    indices = [Index(value = ["quoteId"])],
    foreignKeys = [
        ForeignKey(
            entity = TopicDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["topicId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = QuoteDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["quoteId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class TopicQuoteCrossRefDbEntity(
    val topicId: Long,
    val quoteId: Long
)
