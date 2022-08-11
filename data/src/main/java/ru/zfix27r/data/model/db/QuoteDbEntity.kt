package ru.zfix27r.data.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.zfix27r.domain.model.quote.AddQuoteModel

@Entity(tableName = "quote")
data class QuoteDbEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val text: String,
    val sourceId: Long,
    val usability: Int // 0-10
) {
    companion object {
        fun fromQuote(model: AddQuoteModel): QuoteDbEntity = QuoteDbEntity(
            id = 0,
            text = model.text,
            sourceId = model.sourceId,
            usability = model.usability
        )
    }
}
