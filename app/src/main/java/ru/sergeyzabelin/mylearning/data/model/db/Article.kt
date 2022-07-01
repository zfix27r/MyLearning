package ru.sergeyzabelin.mylearning.data.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Article(

    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val description: String,
    val sourceId: Long
)
