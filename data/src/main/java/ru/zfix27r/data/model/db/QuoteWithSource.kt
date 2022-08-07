package ru.zfix27r.data.model.db

import androidx.room.Embedded
import androidx.room.Relation

data class QuoteWithSource(
    @Embedded
    val quote: Quote,

    @Relation(
        entity = Source::class,
        parentColumn = "sourceId",
        entityColumn = "id"
    )
    val source: Source
)