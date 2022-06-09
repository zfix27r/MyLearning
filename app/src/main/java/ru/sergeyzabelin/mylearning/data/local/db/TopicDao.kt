package ru.sergeyzabelin.mylearning.data.local.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import ru.sergeyzabelin.mylearning.data.model.db.Topic
import ru.sergeyzabelin.mylearning.data.model.db.TopicWithArticles

@Dao
interface TopicDao {

    @Query("SELECT * FROM topic")
    suspend fun getAllTopic(): List<Topic>

    @Transaction
    @Query("SELECT * FROM topic WHERE id = :id LIMIT 1")
    suspend fun getTopicWithArticlesById(id: Int): TopicWithArticles
}