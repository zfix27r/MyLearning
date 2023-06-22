package ru.zfix27r.domain

import kotlinx.coroutines.flow.Flow
import ru.zfix27r.domain.topicEditor.GetTopic4TopicEditorModel
import ru.zfix27r.domain.topicEditor.GetTopics4TopicEditorParentModel
import ru.zfix27r.domain.topicEditor.SaveTopic4TopicEditorModel

interface TopicEditorRepository {
    fun getTopic(id: Int): Flow<GetTopic4TopicEditorModel>

    fun getTopicsByTopicId(
        topicId: Int,
        topicIdSelf: Int
    ): Flow<List<GetTopics4TopicEditorParentModel>>

    fun getTopicsByTopicParentId(
        topicId: Int,
        topicIdSelf: Int
    ): Flow<List<GetTopics4TopicEditorParentModel>>

    fun saveTopic(saveTopicModel: SaveTopic4TopicEditorModel): Flow<Unit>
}