package ru.zfix27r.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.zfix27r.data.local.entity.SourceEntity.Companion.TABLE_SOURCE_NAME

@Entity(tableName = TABLE_SOURCE_NAME)
data class SourceEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID_NAME)
    val id: Long,
    @ColumnInfo(name = TITLE_NAME)
    val title: String,
    @ColumnInfo(name = URL_NAME)
    val url: String,
    @ColumnInfo(name = YEAR_NAME)
    val year: Int?,
) {
    companion object {
        const val TABLE_SOURCE_NAME = "source"

        const val ID_NAME = "id"

        const val TITLE_NAME = "title"
        const val YEAR_NAME = "year"
        const val URL_NAME = "url"
    }
}
