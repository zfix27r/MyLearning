package ru.sergeyzabelin.mylearning.data.local.db.dictionary

import androidx.room.Dao
import androidx.room.Query
import ru.sergeyzabelin.mylearning.data.model.db.Dictionary

@Dao
interface DictionaryDao {

    @Query("SELECT * FROM dictionary")
    suspend fun getAllDictionary(): List<Dictionary>
}