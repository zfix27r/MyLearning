package ru.sergeyzabelin.mylearning.data.model.db

import androidx.room.Embedded
import androidx.room.Relation

data class Dictionary(
    @Embedded
    val topic: Topic,

    @Relation(
        entity = Topic::class,
        parentColumn = "id",
        entityColumn = "parentTopicId"
    )
    var topics: List<TopicWithQuote>
)