package ru.zfix27r.data.model.db

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
    val topics: List<Topic>
)