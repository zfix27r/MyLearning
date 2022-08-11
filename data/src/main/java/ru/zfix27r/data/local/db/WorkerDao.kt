package ru.zfix27r.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import ru.zfix27r.data.model.db.*

@Dao
interface WorkerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setAllTopic(list: List<TopicDbEntity>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setAllQuote(list: List<QuoteDbEntity>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setAllTopicQuoteCrossRef(list: List<TopicQuoteCrossRefDbEntity>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setAllSource(list: List<SourceDbEntity>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setAllQuestion(list: List<QuestionDbEntity>)
}