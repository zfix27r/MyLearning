package ru.zfix27r.data.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "source")
data class SourceDbEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val year: Int,
    val url: String
)
