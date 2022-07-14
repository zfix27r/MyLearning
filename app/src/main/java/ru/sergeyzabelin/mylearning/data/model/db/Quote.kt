package ru.sergeyzabelin.mylearning.data.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Quote(

    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val text: String,
    val sourceId: Long
)
