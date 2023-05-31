package ru.zfix27r.data.local.dao

import androidx.room.*

@Dao
interface DictionaryDao {
/*    @Transaction
    @Query("SELECT * FROM topic WHERE id = :id LIMIT 1")
    fun getDictionary(id: Long): Flow<DictionaryDb>

    @Query("SELECT title, subtitle FROM topic WHERE id = :id LIMIT 1")
    fun getTopic(id: Long): TopicResModel.Data

    @Insert(entity = TopicEntity::class)
    suspend fun insert(addTopicReqModel: AddTopicReqModel): Long

    @Update(entity = TopicEntity::class)
    suspend fun update(saveTopicReqModel: SaveTopicReqModel): Int

    @Transaction
    @Delete(entity = TopicEntity::class)
    suspend fun delete(commonReqModel: CommonReqModel): Int*/
}