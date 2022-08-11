package ru.zfix27r.data.local.db

import androidx.room.*
import ru.zfix27r.data.model.db.DictionaryDb
import ru.zfix27r.data.model.db.TopicDbEntity
import ru.zfix27r.domain.model.AddTopicReqModel
import ru.zfix27r.domain.model.CommonReqModel
import ru.zfix27r.domain.model.SaveTopicReqModel
import ru.zfix27r.domain.model.TopicResModel

@Dao
interface DictionaryDao {
    @Transaction
    @Query("SELECT id, title, subTitle FROM topic WHERE id = :id LIMIT 1")
    fun getDictionary(id: Long): DictionaryDb?

    @Query("SELECT title, subTitle FROM topic WHERE id = :id LIMIT 1")
    fun getTopic(id: Long): TopicResModel.Success?

    @Insert(entity = TopicDbEntity::class)
    suspend fun insert(addTopicReqModel: AddTopicReqModel): Long

    @Update(entity = TopicDbEntity::class)
    suspend fun update(saveTopicReqModel: SaveTopicReqModel): Int

    @Delete(entity = TopicDbEntity::class)
    suspend fun delete(commonReqModel: CommonReqModel): Int
}