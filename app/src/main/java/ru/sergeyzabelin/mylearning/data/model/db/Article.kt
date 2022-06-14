package ru.sergeyzabelin.mylearning.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
/*    foreignKeys = [ForeignKey(
        entity = Topic::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("parent_id"),
        onDelete = ForeignKey.CASCADE
    )]*/
)
data class Article(

    @PrimaryKey(autoGenerate = true)
    val id: Long,

    @ColumnInfo(index = true)
    val parentId: Long,

    val description: String,
    val sourceUrl: String,
    val bookId: Int,
    val difficultyLevel: Int,
    val usability: Int,
    val updateAt: Long
)
