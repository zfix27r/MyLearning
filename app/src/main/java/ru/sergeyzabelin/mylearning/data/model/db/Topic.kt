package ru.sergeyzabelin.mylearning.data.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Topic(

    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val parentTopicId: Long,
    val title: String,
    val subTitle: String,
    val isHasChild: Boolean
)
