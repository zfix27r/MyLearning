package ru.zfix27r.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.zfix27r.data.local.entity.QuoteEntity.Companion.TABLE_QUOTE

@Entity(tableName = TABLE_QUOTE)
data class QuoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val id: Int,
    @ColumnInfo(name = TOPIC_ID)
    val topicId: Int?,
    @ColumnInfo(name = DESCRIPTION)
    val description: String,
    @ColumnInfo(name = USABILITY)
    val usability: Int?,
    @ColumnInfo(name = FAVORITE)
    val favorite: Int?,
) {
    companion object {
        const val TABLE_QUOTE = "quote"

        const val ID = "id"
        const val TOPIC_ID = "topic_id"

        const val DESCRIPTION = "description"
        const val USABILITY = "usability"

        const val FAVORITE = "favorite"
    }
}