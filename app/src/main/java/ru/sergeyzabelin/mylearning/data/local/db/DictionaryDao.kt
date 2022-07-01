package ru.sergeyzabelin.mylearning.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import ru.sergeyzabelin.mylearning.data.model.db.Article
import ru.sergeyzabelin.mylearning.data.model.db.Dictionary
import ru.sergeyzabelin.mylearning.data.model.db.Topic

@Dao
interface DictionaryDao {

    @Transaction
    @Query("SELECT * FROM topic WHERE parentTopicId = :parentTopicId")
    fun getDictionaryBy(parentTopicId: Long): LiveData<List<Dictionary>>

    @Update
    suspend fun setTopic(topic: Topic)

    @Update
    suspend fun setArticle(article: Article)
}