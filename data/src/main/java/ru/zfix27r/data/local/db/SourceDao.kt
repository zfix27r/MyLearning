package ru.zfix27r.data.local.db

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import ru.zfix27r.data.model.db.Source

interface SourceDao {
    @Insert
    suspend fun insert(source: Source): Long
    @Update
    suspend fun update(source: Source): Int
    @Delete
    suspend fun delete(source: Source): Int
}