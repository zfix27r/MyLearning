package ru.zfix27r.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.zfix27r.data.local.entity.TopicEntity
import ru.zfix27r.data.local.model.DeleteTopicModel
import ru.zfix27r.data.local.model.GetTopicEditorDataModel
import ru.zfix27r.data.local.model.SaveTopicModel

@Dao
interface TopicDao {
    @Query(
        "SELECT t1.icon_id, t1.title, t1.subtitle, t2.id as topic_parent_id, t2.title as topic_parent_title, t2.subtitle as topic_parent_subtitle " +
                "FROM topic t1 " +
                "LEFT JOIN topic t2 ON t2.id = t1.parent_id " +
                "WHERE t1.id = :topicId " +
                "LIMIT 1"
    )
    fun getTopicEditorDataModel(topicId: Int): Flow<GetTopicEditorDataModel>

    @Query(
        "SELECT * " +
                "FROM topic " +
                "WHERE " +
                "CASE " +
                "WHEN :topicId > 0 THEN parent_id = :topicId " +
                "ELSE parent_id IS NULL " +
                "END"
    )
    fun getTopicsByTopicId(topicId: Int): Flow<List<TopicEntity>>

    @Query(
        "SELECT *, (SELECT parent_id FROM topic WHERE id = :topicId LIMIT 1) topic_parent_id " +
                "FROM topic " +
                "WHERE " +
                "CASE " +
                "WHEN topic_parent_id IS NULL THEN parent_id IS NULL " +
                "ELSE parent_id = topic_parent_id " +
                "END"
    )
    fun getTopicsByTopicParentId(topicId: Int): Flow<List<TopicEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = TopicEntity::class)
    fun save(saveTopicModel: SaveTopicModel): Long

    @Delete(entity = TopicEntity::class)
    fun delete(deleteTopicModel: DeleteTopicModel): Int
}