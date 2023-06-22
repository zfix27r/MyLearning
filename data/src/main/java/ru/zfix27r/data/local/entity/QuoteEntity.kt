package ru.zfix27r.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.zfix27r.data.local.entity.QuoteEntity.Companion.TABLE_QUOTE_NAME

@Entity(tableName = TABLE_QUOTE_NAME)
data class QuoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID_NAME)
    val id: Int,
    @ColumnInfo(name = SOURCE_ID_NAME)
    val sourceId: Int,
    @ColumnInfo(name = DESCRIPTION_NAME)
    val description: String,
    @ColumnInfo(name = USABILITY_NAME)
    val usability: Int?,
) {
    companion object {
        const val TABLE_QUOTE_NAME = "quote"

        const val ID_NAME = "id"
        const val SOURCE_ID_NAME = "source_id"

        const val DESCRIPTION_NAME = "description"
        const val USABILITY_NAME = "usability"
    }
}