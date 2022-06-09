package ru.sergeyzabelin.mylearning.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Topic::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("topic_id")
    )]
)
data class Article(
    @PrimaryKey(autoGenerate = true) val id: Int,

    @ColumnInfo(name = "topic_id") val topicId: Int,

    val description: String,
    val url: String
)
