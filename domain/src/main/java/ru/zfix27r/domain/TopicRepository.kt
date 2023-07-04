package ru.zfix27r.domain

import kotlinx.coroutines.flow.Flow
import ru.zfix27r.domain.editor.topic.GetTopicEditorModel
import ru.zfix27r.domain.editor.topic.SaveTopicEditorModel
import ru.zfix27r.domain.editor.topic.parent.GetTopicEditorParentModel

interface TopicRepository {
    fun getTopicEditorModel(topicId: Int): Flow<GetTopicEditorModel>

    fun getTopicEditorParentModelByTopicId(topicId: Int): Flow<List<GetTopicEditorParentModel>>

    fun getTopicEditorParentModelByTopicParentId(topicId: Int): Flow<List<GetTopicEditorParentModel>>

    fun saveTopicEditorModel(saveTopicEditorModel: SaveTopicEditorModel): Flow<Unit>

    fun deleteTopic(topicId: Int): Flow<Unit>
}