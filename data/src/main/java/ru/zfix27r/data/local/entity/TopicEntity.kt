package ru.zfix27r.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.zfix27r.data.local.entity.TopicEntity.Companion.TABLE_TOPIC

@Entity(tableName = TABLE_TOPIC)
data class TopicEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val id: Int,
    @ColumnInfo(name = PARENT_ID)
    val parentId: Int?,
    @ColumnInfo(name = TITLE)
    val title: String,
    @ColumnInfo(name = SUBTITLE)
    val subtitle: String?,
    @ColumnInfo(name = DIFFICULTY)
    val difficulty: Int?
) {
    companion object {
        const val TABLE_TOPIC = "topic"

        const val ID = "id"
        const val PARENT_ID = "parent_id"

        const val TITLE = "title"
        const val SUBTITLE = "subtitle"

        const val DIFFICULTY = "difficulty"
    }
}