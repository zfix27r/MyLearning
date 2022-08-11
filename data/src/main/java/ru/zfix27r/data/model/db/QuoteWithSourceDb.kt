package ru.zfix27r.data.model.db

import androidx.room.Embedded
import androidx.room.Relation

data class QuoteWithSourceDb(
    @Embedded
    val quote: QuoteDbEntity,

    @Relation(
        entity = SourceDbEntity::class,
        parentColumn = "sourceId",
        entityColumn = "id"
    )
    val source: SourceDbEntity
)