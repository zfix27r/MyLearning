package ru.zfix27r.data.local.db

import androidx.room.Embedded
import androidx.room.Relation
import ru.zfix27r.data.local.entity.QuoteEntity
import ru.zfix27r.data.local.entity.SourceEntity
import ru.zfix27r.domain.model.content.ContentDataModel

data class QuoteWithSourceDb(
    @Embedded
    val quote: ContentDataModel.QuoteWithSource.Quote,

    @Relation(
        entity = SourceEntity::class,
        parentColumn = QuoteEntity.SOURCE_ID_NAME,
        entityColumn = SourceEntity.ID_NAME
    )
    val source: ContentDataModel.QuoteWithSource.Source
)