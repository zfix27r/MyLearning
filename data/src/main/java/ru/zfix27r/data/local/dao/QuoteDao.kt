package ru.zfix27r.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.zfix27r.data.local.entity.QuoteEntity
import ru.zfix27r.data.local.model.GetQuoteEditorDataModel
import ru.zfix27r.data.local.model.SaveQuoteModel

@Dao
interface QuoteDao {
    @Query(
        "SELECT " +
                "q.description, " +
                "t.id ${GetQuoteEditorDataModel.TOPIC_ID}, " +
                "t.icon_id ${GetQuoteEditorDataModel.TOPIC_ICON_ID}, " +
                "t.title ${GetQuoteEditorDataModel.TOPIC_TITLE}, " +
                "t.subtitle ${GetQuoteEditorDataModel.TOPIC_SUBTITLE} " +
                "FROM quote q " +
                "LEFT JOIN topic t ON t.id = q.topic_id " +
                "WHERE q.id = :quoteId " +
                "LIMIT 1"
    )
    fun getGetQuoteEditorDataModel(quoteId: Int): Flow<GetQuoteEditorDataModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = QuoteEntity::class)
    fun saveQuote(saveQuoteModel: SaveQuoteModel): Long
}