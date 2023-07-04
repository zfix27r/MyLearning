package ru.zfix27r.data.local.model

import androidx.room.ColumnInfo
import ru.zfix27r.data.local.entity.QuoteEntity

data class SaveQuoteModel(
    @ColumnInfo(name = QuoteEntity.ID)
    val id: Int?,

    @ColumnInfo(name = QuoteEntity.TOPIC_ID)
    val topicId: Int?,

    @ColumnInfo(name = QuoteEntity.DESCRIPTION)
    val description: String,
)