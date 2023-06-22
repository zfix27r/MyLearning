package ru.zfix27r.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.zfix27r.data.local.entity.QuestionEntity.Companion.TABLE_QUESTION

@Entity(tableName = TABLE_QUESTION)
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val id: Int,
    @ColumnInfo(name = DESCRIPTION)
    val description: String,
    @ColumnInfo(name = TOPIC_ID)
    val topicId: Int,
    @ColumnInfo(name = QUOTE_IDS)
    val quoteIds: String
) {
    companion object {
        const val TABLE_QUESTION = "question"

        const val ID = "id"
        const val DESCRIPTION = "description"
        const val TOPIC_ID = "topic_id"
        const val QUOTE_IDS = "quote_ids"
    }
}