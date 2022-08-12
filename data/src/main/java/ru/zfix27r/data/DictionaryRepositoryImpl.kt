package ru.zfix27r.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.zfix27r.data.local.db.DictionaryDao
import ru.zfix27r.domain.model.*
import ru.zfix27r.domain.model.common.ResponseType
import ru.zfix27r.domain.repository.DictionaryRepository
import javax.inject.Inject

class DictionaryRepositoryImpl @Inject constructor(private val dao: DictionaryDao) :
    DictionaryRepository {
    override fun getDictionary(commonReqModel: CommonReqModel): Flow<DictionaryResModel> =
        flow {
            val dictionary = dao.getDictionary(commonReqModel.id)
            emit(dictionary.toDictionary())
        }

    override fun getTopic(commonReqModel: CommonReqModel): Flow<TopicResModel> =
        flow {
            val topic = dao.getTopic(commonReqModel.id)
            emit(topic)
        }

    override suspend fun addTopic(addTopicReqModel: AddTopicReqModel): Flow<ResponseModel> =
        flow {
            val result = dao.insert(addTopicReqModel)
            if (result > 0) emit(ResponseModel(ResponseType.SUCCESS))
            else emit(ResponseModel(ResponseType.UNKNOWN_ERROR))
        }

    override suspend fun saveTopic(saveTopicReqModel: SaveTopicReqModel): Flow<ResponseModel> =
        flow {
            val result = dao.update(saveTopicReqModel)
            if (result == 1) emit(ResponseModel(ResponseType.SUCCESS))
            else emit(ResponseModel(ResponseType.UNKNOWN_ERROR))
        }

    override suspend fun deleteTopic(commonReqModel: CommonReqModel): Flow<ResponseModel> =
        flow {
            val result = dao.delete(commonReqModel)
            if (result == 1) emit(ResponseModel(ResponseType.UNKNOWN_ERROR))
            else emit(ResponseModel(ResponseType.SUCCESS))
        }
}