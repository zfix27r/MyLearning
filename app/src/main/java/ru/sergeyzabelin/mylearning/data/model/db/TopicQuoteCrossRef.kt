package ru.sergeyzabelin.mylearning.data.model.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    primaryKeys = ["topicId", "quoteId"],
    indices = [Index(value = ["quoteId"])],
    foreignKeys = [
        ForeignKey(
            entity = Topic::class,
            parentColumns = ["id"],
            childColumns = ["topicId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Quote::class,
            parentColumns = ["id"],
            childColumns = ["quoteId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class TopicQuoteCrossRef(
    val topicId: Long,
    val quoteId: Long
)
