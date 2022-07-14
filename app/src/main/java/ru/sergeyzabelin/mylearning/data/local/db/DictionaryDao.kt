package ru.sergeyzabelin.mylearning.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import ru.sergeyzabelin.mylearning.data.model.db.Dictionary
import ru.sergeyzabelin.mylearning.data.model.db.Quote
import ru.sergeyzabelin.mylearning.data.model.db.Topic

@Dao
interface DictionaryDao {

    @Transaction
    @Query("SELECT * FROM topic WHERE id = :id LIMIT 1")
    fun getDictionaryBy(id: Long): LiveData<Dictionary>

    @Query("SELECT * FROM topic WHERE id = :id LIMIT 1")
    fun getDictionaryTopicBy(id: Long): LiveData<Topic>

    @Update(onConflict = REPLACE)
    suspend fun setTopic(topic: Topic)

    @Insert
    suspend fun addTopic(topic: Topic)

    @Delete
    suspend fun deleteTopic(topic: Topic)

    @Update(onConflict = REPLACE)
    suspend fun setArticle(article: Quote)

    @Insert
    suspend fun addArticle(article: Quote)

}