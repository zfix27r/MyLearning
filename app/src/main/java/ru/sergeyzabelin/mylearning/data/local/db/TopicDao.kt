package ru.sergeyzabelin.mylearning.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import ru.sergeyzabelin.mylearning.data.model.db.Article
import ru.sergeyzabelin.mylearning.data.model.db.DictionaryData

@Dao
interface TopicDao {

    @Transaction
    @Query("SELECT * FROM topic WHERE id = :id LIMIT 1")
    fun getDictionaryDataById(id:Long): LiveData<DictionaryData>

/*    @Transaction
    @Query("SELECT * FROM topic WHERE id = :id LIMIT 1")
    fun getTopicWithArticlesById(id: Long): LiveData<TopicWithArticles>*/

    @Update
    suspend fun setArticle(article: Article)
}