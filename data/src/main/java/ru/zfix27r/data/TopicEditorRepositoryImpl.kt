package ru.zfix27r.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import ru.zfix27r.data.error.DBQueryError
import ru.zfix27r.data.local.dao.TopicEditorDao
import ru.zfix27r.data.local.entity.TopicEntity
import ru.zfix27r.data.local.model.GetTopicDataModel
import ru.zfix27r.domain.TopicEditorRepository
import ru.zfix27r.domain.topicEditor.GetTopic4TopicEditorModel
import ru.zfix27r.domain.topicEditor.GetTopics4TopicEditorParentModel
import ru.zfix27r.domain.topicEditor.SaveTopic4TopicEditorModel
import javax.inject.Inject

class TopicEditorRepositoryImpl @Inject constructor(
    private val dao: TopicEditorDao,
) : TopicEditorRepository {
    override fun getTopic(id: Int) = dao.getTopic(id).map { it.toGetTopicModel() }

    override fun getTopicsByTopicId(
        topicId: Int,
        topicIdSelf: Int
    ): Flow<List<GetTopics4TopicEditorParentModel>> =
        dao.getTopicsByTopicId(topicId, topicIdSelf)
            .map { it.toListGetTopics4TopicEditorParentModel() }

    override fun getTopicsByTopicParentId(
        topicId: Int,
        topicIdSelf: Int
    ): Flow<List<GetTopics4TopicEditorParentModel>> =
        dao.getTopicsByTopicParentId(topicId, topicIdSelf)
            .map { it.toListGetTopics4TopicEditorParentModel() }

    override fun saveTopic(saveTopicModel: SaveTopic4TopicEditorModel) = flow {
        if (dao.saveTopic(saveTopicModel.toSaveTopicModel()) == 1L) emit(Unit)
        else throw DBQueryError()
    }

    private fun GetTopicDataModel.toGetTopicModel() =
        GetTopic4TopicEditorModel(
            id,
            parentId,
            parentTitle,
            parentSubtitle,
            title,
            subtitle,
            difficulty
        )

    private fun SaveTopic4TopicEditorModel.toSaveTopicModel() =
        ru.zfix27r.data.local.model.SaveTopicModel(id, parentId, title, subtitle, difficulty)

    private fun List<TopicEntity>.toListGetTopics4TopicEditorParentModel() =
        map {
            it.run { GetTopics4TopicEditorParentModel(id, parentId ?: 0, title, subtitle) }
        }
}