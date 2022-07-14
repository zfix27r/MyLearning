package ru.sergeyzabelin.mylearning.data.model.db

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class TopicWithQuote(
    @Embedded
    val topic: Topic,

    @Relation(
        entity = Quote::class,
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = TopicQuoteCrossRef::class,
            parentColumn = "topicId",
            entityColumn = "quoteId"
        )
    )
    var quotes: List<Quote>
)