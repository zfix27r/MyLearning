package ru.zfix27r.data.local.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import ru.zfix27r.data.model.db.ContentDb

@Dao
interface ContentDao {
    @Transaction
    @Query("SELECT id, title, subtitle FROM topic WHERE id = :id LIMIT 1")
    fun getContent(id: Long): Flow<ContentDb>
}