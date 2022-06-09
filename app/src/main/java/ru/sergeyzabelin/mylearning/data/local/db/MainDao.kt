package ru.sergeyzabelin.mylearning.data.local.db

import androidx.room.Dao
import androidx.room.Query
import ru.sergeyzabelin.mylearning.data.model.db.Topic

@Dao
interface MainDao {

    // TODO что-то для первого запуска и ожидания работы AppDatabaseWorker
    @Query("SELECT * FROM topic WHERE id == 1 LIMIT 1")
    suspend fun firstRun(): Topic
}