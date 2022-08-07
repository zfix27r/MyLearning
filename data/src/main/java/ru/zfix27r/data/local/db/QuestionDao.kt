package ru.zfix27r.data.local.db

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import ru.zfix27r.data.model.db.Question

interface QuestionDao {
    @Insert
    suspend fun insert(question: Question): Long
    @Update
    suspend fun update(question: Question): Int
    @Delete
    suspend fun delete(question: Question): Int
}