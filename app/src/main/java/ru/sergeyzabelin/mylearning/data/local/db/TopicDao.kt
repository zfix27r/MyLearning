package ru.sergeyzabelin.mylearning.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import ru.sergeyzabelin.mylearning.data.model.db.Article
import ru.sergeyzabelin.mylearning.data.model.db.Topic
import ru.sergeyzabelin.mylearning.data.model.db.TopicWithArticles

@Dao
interface TopicDao {

    @Query("SELECT * FROM topic")
    suspend fun getAllTopic(): List<Topic>

    @Transaction
    @Query("SELECT * FROM topic WHERE id = :id LIMIT 1")
    fun getTopicWithArticlesById(id: Long): LiveData<TopicWithArticles>

    @Update
    suspend fun setArticle(article: Article)
}