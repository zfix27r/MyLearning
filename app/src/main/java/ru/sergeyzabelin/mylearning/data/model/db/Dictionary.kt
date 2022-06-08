package ru.sergeyzabelin.mylearning.data.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Dictionary(
    @PrimaryKey(autoGenerate = true) val id: Int,

    val title: String,
    val name: String,
    val description: String
)
