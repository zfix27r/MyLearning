package ru.zfix27r.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ru.zfix27r.data.model.db.Topic

interface TopicDao {
    @Query("SELECT * FROM topic WHERE id = :id LIMIT 1")
    fun getTopic(id: Long): LiveData<Topic>

    @Insert
    suspend fun insert(topic: Topic): Long

    @Update
    suspend fun update(topic: Topic): Int

    @Delete
    suspend fun delete(topic: Topic): Int
}