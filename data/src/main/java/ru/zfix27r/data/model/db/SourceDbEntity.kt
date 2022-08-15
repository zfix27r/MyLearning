package ru.zfix27r.data.model.db

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.zfix27r.data.model.db.SourceDbEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class SourceDbEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val id: Long,
    @NonNull
    val title: String,
    val year: Int,
    @NonNull
    val url: String
) {
    companion object {
        const val TABLE_NAME = "source"
        const val ID = "id"
    }
}
