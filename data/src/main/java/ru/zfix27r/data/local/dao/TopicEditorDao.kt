package ru.zfix27r.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.zfix27r.data.local.entity.TopicEntity
import ru.zfix27r.data.local.model.GetTopicDataModel
import ru.zfix27r.data.local.model.SaveTopicModel

@Dao
interface TopicEditorDao {
    @Query(
        "SELECT t1.*, t2.title parent_topic_title, t2.subtitle parent_topic_subtitle " +
                "FROM topic t1 " +
                "LEFT JOIN topic t2 ON t1.parent_id = t2.id " +
                "WHERE t1.id = :topicId LIMIT 1"
    )
    fun getTopic(topicId: Int): Flow<GetTopicDataModel>

    @Query("SELECT * FROM topic WHERE parent_id = :topicId AND id != :topicIdSelf")
    fun getTopicsByTopicId(topicId: Int, topicIdSelf: Int): Flow<List<TopicEntity>>

    @Query(
        "SELECT t1.*, (SELECT parent_id FROM topic WHERE id = :topicId LIMIT 1) t2_parent_id " +
                "FROM topic t1 " +
                "WHERE t1.id != :topicIdSelf AND " +
                "CASE " +
                "WHEN t2_parent_id IS NULL THEN t1.parent_id IS NULL " +
                "ELSE t1.parent_id = t2_parent_id " +
                "END"
    )
    fun getTopicsByTopicParentId(topicId: Int, topicIdSelf: Int): Flow<List<TopicEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = TopicEntity::class)
    fun saveTopic(saveTopicModel: SaveTopicModel): Long
}