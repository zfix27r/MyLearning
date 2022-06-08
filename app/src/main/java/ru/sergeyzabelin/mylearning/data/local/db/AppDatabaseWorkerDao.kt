package ru.sergeyzabelin.mylearning.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import ru.sergeyzabelin.mylearning.data.model.db.Dictionary

@Dao
interface AppDatabaseWorkerDao {

    @Insert
    suspend fun setAllDictionary(dictionary: List<Dictionary>)
}