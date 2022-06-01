package ru.sergeyzabelin.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LessonContent(
    @PrimaryKey(autoGenerate = true) val id: Int,

    val title: String,
    val description: String,
    val content: String,
    val image: String
)
