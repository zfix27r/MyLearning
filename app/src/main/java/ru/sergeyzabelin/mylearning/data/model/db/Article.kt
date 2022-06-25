package ru.sergeyzabelin.mylearning.data.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Article(

    @PrimaryKey(autoGenerate = true)
    val id: Long,

    val description: String,
    // ссылка на источник, 0 - нет
    val sourceUrl: String,
    // ид на книгу, 0 - нет
    val bookId: Int,
    // сложность понимания [0-5]
    val difficulty: Float,
    // полезность [0-5]
    val usability: Float,

    val updateAt: Long
)
