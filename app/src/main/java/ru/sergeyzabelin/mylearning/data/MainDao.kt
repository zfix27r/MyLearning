package ru.sergeyzabelin.mylearning.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.sergeyzabelin.mylearning.data.entities.Dictionary

@Dao
interface MainDao {

    @Query("SELECT * FROM dictionary")
    fun getAllDictionary(): LiveData<List<Dictionary>>

    @Insert
    suspend fun setAllDictionary(dictionaryList: List<Dictionary>)
}