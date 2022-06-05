package ru.sergeyzabelin.mylearning.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Dictionary(
    @PrimaryKey(autoGenerate = true) val id: Int,

    val title: String,
    val name: String,
    val description: String,
    val source: List<String>
)
