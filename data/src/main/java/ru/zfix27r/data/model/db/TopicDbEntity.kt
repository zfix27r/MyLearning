package ru.zfix27r.data.model.db

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.zfix27r.data.model.db.TopicDbEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class TopicDbEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val id: Long,
    @NonNull @ColumnInfo(name = PARENT_ID)
    val parentId: Long,
    @NonNull
    val title: String,
    val subtitle: String,
    val difficulty: Int
) {
    companion object {
        const val TABLE_NAME = "topic"
        const val ID = "id"
        const val PARENT_ID = "parentId"
    }
}
