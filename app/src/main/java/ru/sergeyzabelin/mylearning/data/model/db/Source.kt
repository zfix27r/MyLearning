package ru.sergeyzabelin.mylearning.data.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Source(

    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val author: String,
    val date: String,
    val url: String,
    // сложность понимания [0-5]
    val difficulty: Float,
    // полезность [0-5]
    val usability: Float
)
