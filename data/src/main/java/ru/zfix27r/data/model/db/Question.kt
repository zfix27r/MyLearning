package ru.zfix27r.data.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Question(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val topicId: Long,
    val text: String,
    val quotesId: String
)
