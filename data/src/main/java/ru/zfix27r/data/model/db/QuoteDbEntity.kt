package ru.zfix27r.data.model.db

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.zfix27r.data.model.db.QuoteDbEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class QuoteDbEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val id: Long,
    @NonNull
    val text: String,
    @ColumnInfo(name = SOURCE_ID)
    val sourceId: Long,
    val usability: Int // 0-10
) {
    companion object {
        const val TABLE_NAME = "quote"
        const val ID = "id"
        const val SOURCE_ID = "sourceId"
    }
}
