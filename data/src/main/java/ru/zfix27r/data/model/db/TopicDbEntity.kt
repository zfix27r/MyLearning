package ru.zfix27r.data.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "topic")
data class TopicDbEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val parentId: Long,
    val title: String,
    val subTitle: String,
    val difficulty: Int // 0-10
)
