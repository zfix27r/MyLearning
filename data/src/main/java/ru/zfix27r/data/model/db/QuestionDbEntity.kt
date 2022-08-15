package ru.zfix27r.data.model.db

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.zfix27r.data.model.db.QuestionDbEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class QuestionDbEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @NonNull
    val text: String,
    @NonNull @ColumnInfo(name = TOPIC_ID)
    val topicId: Long,
    @ColumnInfo(name = QUOTES_ID)
    val quotesId: String
) {
    companion object {
        const val TABLE_NAME = "question"
        const val TOPIC_ID = "topic_id"
        const val QUOTES_ID = "quotes_id"
    }
}
