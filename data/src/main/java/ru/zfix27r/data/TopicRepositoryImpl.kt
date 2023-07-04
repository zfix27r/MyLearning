package ru.zfix27r.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import ru.zfix27r.data.error.DBQueryError
import ru.zfix27r.data.local.dao.TopicDao
import ru.zfix27r.data.local.entity.TopicEntity
import ru.zfix27r.data.local.model.GetTopicEditorDataModel
import ru.zfix27r.data.local.model.SaveTopicModel
import ru.zfix27r.domain.TopicRepository
import ru.zfix27r.domain.editor.topic.GetTopicEditorModel
import ru.zfix27r.domain.editor.topic.SaveTopicEditorModel
import ru.zfix27r.domain.editor.topic.parent.GetTopicEditorParentModel
import javax.inject.Inject

class TopicRepositoryImpl @Inject constructor(
    private val dao: TopicDao,
) : TopicRepository {
    override fun getTopicEditorModel(topicId: Int) =
        dao.getTopicEditorDataModel(topicId).map { it.toGetTopicEditorModel() }

    override fun getTopicEditorParentModelByTopicId(topicId: Int) =
        dao.getTopicsByTopicId(topicId)
            .map { it.toListGetTopicEditorParentModel() }

    override fun getTopicEditorParentModelByTopicParentId(topicId: Int) =
        dao.getTopicsByTopicParentId(topicId)
            .map { it.toListGetTopicEditorParentModel() }

    override fun saveTopicEditorModel(saveTopicEditorModel: SaveTopicEditorModel) = flow {
        val response = dao.save(saveTopicEditorModel.toSaveTopicModel())
        if (response > 0) emit(Unit)
        else throw DBQueryError("DB: $saveTopicEditorModel \nResponse: $response")
    }

    override fun deleteTopic(topicId: Int): Flow<Unit> {
        TODO("Not yet implemented")
    }

    private fun GetTopicEditorDataModel.toGetTopicEditorModel() = GetTopicEditorModel(
        iconId, title, subtitle, topicParentId, topicParentTitle, topicParentSubtitle
    )

    private fun SaveTopicEditorModel.toSaveTopicModel() =
        SaveTopicModel(id, parentId, iconId, title, subtitle)

    private fun List<TopicEntity>.toListGetTopicEditorParentModel() =
        map { it.run { GetTopicEditorParentModel(id, parentId, title, subtitle, childCount) } }
}