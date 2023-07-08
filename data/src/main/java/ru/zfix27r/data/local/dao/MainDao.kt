package ru.zfix27r.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.zfix27r.data.local.model.GetMainDataModel

@Dao
interface MainDao {
    @Query(
        "SELECT " +
                "q.id ${GetMainDataModel.QUOTE_ID}, " +
                "q.description ${GetMainDataModel.QUOTE_DESCRIPTION}, " +
                "t.id ${GetMainDataModel.TOPIC_ID}, " +
                "t.icon_id ${GetMainDataModel.TOPIC_ICON_ID}, " +
                "t.title ${GetMainDataModel.TOPIC_TITLE}, " +
                "t.subtitle ${GetMainDataModel.TOPIC_SUBTITLE} " +
                "FROM quote q " +
                "JOIN topic t ON t.id = q.topic_id " +
                "ORDER BY q.id " +
                "LIMIT 50"
    )
    fun getMain(): Flow<List<GetMainDataModel>>
}