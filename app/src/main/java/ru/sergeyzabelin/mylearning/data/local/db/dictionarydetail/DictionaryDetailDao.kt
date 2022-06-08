package ru.sergeyzabelin.mylearning.data.local.db.dictionarydetail

import androidx.room.Dao
import androidx.room.Query
import ru.sergeyzabelin.mylearning.data.model.db.Dictionary

@Dao
interface DictionaryDetailDao {

    @Query("SELECT * FROM dictionary WHERE id = :id LIMIT 1")
    suspend fun getDictionaryDetailById(id: Int): Dictionary
}