package ru.zfix27r.data.local.db

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import ru.zfix27r.data.model.db.Quote

interface QuoteDao {
    @Insert
    suspend fun insert(quote: Quote): Long
    @Update
    suspend fun update(quote: Quote): Int
    @Delete
    suspend fun delete(quote: Quote): Int
}