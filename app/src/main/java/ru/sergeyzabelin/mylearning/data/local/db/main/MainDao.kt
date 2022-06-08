package ru.sergeyzabelin.mylearning.data.local.db.main

import androidx.room.Dao
import androidx.room.Query
import ru.sergeyzabelin.mylearning.data.model.db.Dictionary

@Dao
interface MainDao {

    // TODO что-то для первого запуска и ожидания работы AppDatabaseWorker
    @Query("SELECT * FROM dictionary WHERE id == 1 LIMIT 1")
    suspend fun firstRun(): Dictionary
}