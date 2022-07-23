package ru.sergeyzabelin.mylearning.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import ru.sergeyzabelin.mylearning.data.model.db.*

@Dao
interface DictionaryDao {

    @Transaction
    @Query("SELECT * FROM topic WHERE id = :id LIMIT 1")
    fun getDictionaryBy(id: Long): LiveData<Dictionary>

    @Query("SELECT * FROM topic WHERE id = :id LIMIT 1")
    fun getTopicBy(id: Long): LiveData<Topic>

    @Transaction
    @Query("SELECT * FROM topic WHERE id = :id LIMIT 1")
    fun getTopicWithQuoteBy(id: Long): LiveData<Content>

    @Insert(onConflict = REPLACE)
    suspend fun save(topic: Topic)
    @Insert(onConflict = REPLACE)
    suspend fun save(quote: Quote)
    @Insert(onConflict = REPLACE)
    suspend fun save(source: Source)
    @Insert(onConflict = REPLACE)
    suspend fun save(question: Question)

    @Delete
    suspend fun delete(topic: Topic)
    @Delete
    suspend fun delete(quote: Quote)
    @Delete
    suspend fun delete(source: Source)
    @Delete
    suspend fun delete(question: Question)
}