package ru.zfix27r.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import ru.zfix27r.data.model.db.Dictionary

@Dao
interface DictionaryDao {
    @Transaction
    @Query("SELECT * FROM topic WHERE id = :id LIMIT 1")
    fun getDictionary(id: Long): LiveData<Dictionary>
}