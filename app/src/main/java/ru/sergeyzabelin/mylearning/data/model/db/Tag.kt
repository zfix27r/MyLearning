package ru.sergeyzabelin.mylearning.data.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tag(
    @PrimaryKey(autoGenerate = true)
    val id: Long,

    val title: String,
    val label: String
)
