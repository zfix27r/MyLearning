package ru.zfix27r.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.zfix27r.data.local.model.activity.SearchDataModel

@Dao
interface ActivityDao {
    @Query("SELECT * " +
            "FROM topic " +
            "WHERE title LIKE :query")
    fun search(query: String) : Flow<List<SearchDataModel>>
}