package ru.zfix27r.domain.repository

import ru.zfix27r.domain.model.topic.*

interface TopicRepository {
    fun getTopicById(topicReqModel: TopicReqModel): TopicResModel
    suspend fun add(addTopicModel: AddTopicModel)
    suspend fun save(saveTopicModel: SaveTopicModel)
    suspend fun delete(deleteTopicModel: DeleteTopicModel)
}