package ru.zfix27r.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.zfix27r.domain.model.*

interface DictionaryRepository {
    fun getDictionary(commonReqModel: CommonReqModel): Flow<DictionaryResModel>

    fun getTopic(commonReqModel: CommonReqModel): Flow<TopicResModel>
    suspend fun addTopic(addTopicReqModel: AddTopicReqModel): Flow<CommonResModel?>
    suspend fun saveTopic(saveTopicReqModel: SaveTopicReqModel): Flow<CommonResModel?>
    suspend fun deleteTopic(commonReqModel: CommonReqModel): Flow<CommonResModel?>
}