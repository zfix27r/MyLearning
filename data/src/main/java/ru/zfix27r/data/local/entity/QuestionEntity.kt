package ru.zfix27r.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.zfix27r.data.local.entity.QuestionEntity.Companion.TABLE_QUESTION

@Entity(tableName = TABLE_QUESTION)
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val id: Long,
    @ColumnInfo(name = TEXT)
    val text: String,
    @ColumnInfo(name = TOPIC_ID)
    val topicId: Long,
    @ColumnInfo(name = QUOTES_ID)
    val quotesId: String
) {
    companion object {
        const val TABLE_QUESTION = "question"

        const val ID = "id"
        const val TEXT = "text"
        const val TOPIC_ID = "topic_id"
        const val QUOTES_ID = "quotes_id"
    }
}