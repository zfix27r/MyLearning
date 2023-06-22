package ru.zfix27r.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.zfix27r.data.local.entity.TopicEntity
import ru.zfix27r.data.local.model.DeleteTopicModel

@Dao
interface MainDao {
    @Query("SELECT * " +
            "FROM topic " +
            "WHERE " +
            "CASE " +
            "WHEN :id > 0 THEN  parent_id = :id " +
            "ELSE parent_id IS NULL " +
            "END")
    fun getTopicsByParentId(id: Int): Flow<List<TopicEntity>>

    @Delete(entity = TopicEntity::class)
    fun delete(deleteTopicModel: DeleteTopicModel): Int
}