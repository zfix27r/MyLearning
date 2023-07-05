package ru.zfix27r.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.zfix27r.data.local.entity.QuoteEntity
import ru.zfix27r.data.local.entity.TopicEntity

@Dao
interface MainDao {

    @Query("SELECT * FROM topic WHERE id = :topicId LIMIT 1")
    fun getMain(topicId: Int): Flow<TopicEntity?>

    @Query(
        "SELECT * " +
                "FROM topic " +
                "WHERE " +
                "CASE " +
                "WHEN :topicId > 0 THEN parent_id = :topicId " +
                "ELSE parent_id IS NULL " +
                "END"
    )
    fun getTopics(topicId: Int): Flow<List<TopicEntity>>

    @Query(
        "SELECT * " +
                "FROM quote " +
                "WHERE " +
                "CASE " +
                "WHEN :topicId > 0 THEN topic_id = :topicId " +
                "ELSE topic_id IS NULL " +
                "END"
    )
    fun getQuotes(topicId: Int): Flow<List<QuoteEntity>>
}