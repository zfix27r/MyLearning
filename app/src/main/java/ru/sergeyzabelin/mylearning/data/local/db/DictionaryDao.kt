package ru.sergeyzabelin.mylearning.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.*
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


    @Insert
    suspend fun insert(topic: Topic): Long

    @Insert
    suspend fun insert(quote: Quote): Long

    @Insert
    suspend fun insert(source: Source): Long

    @Insert
    suspend fun insert(question: Question): Long

    @Update
    suspend fun update(topic: Topic): Int

    @Update
    suspend fun update(quote: Quote): Int

    @Update
    suspend fun update(source: Source): Int

    @Update
    suspend fun update(question: Question): Int

    @Delete
    suspend fun delete(topic: Topic): Int

    @Delete
    suspend fun delete(quote: Quote): Int

    @Delete
    suspend fun delete(source: Source): Int

    @Delete
    suspend fun delete(question: Question): Int
}