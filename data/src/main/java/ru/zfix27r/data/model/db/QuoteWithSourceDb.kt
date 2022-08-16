package ru.zfix27r.data.model.db

import androidx.room.Embedded
import androidx.room.Relation
import ru.zfix27r.domain.model.content.ContentDataModel

data class QuoteWithSourceDb(
    @Embedded
    val quote: ContentDataModel.QuoteWithSource.Quote,

    @Relation(
        entity = SourceDbEntity::class,
        parentColumn = QuoteDbEntity.SOURCE_ID,
        entityColumn = SourceDbEntity.ID
    )
    val source: ContentDataModel.QuoteWithSource.Source
)