package ru.zfix27r.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.zfix27r.data.local.db.DictionaryDao
import ru.zfix27r.domain.model.*
import ru.zfix27r.domain.model.common.ErrorType
import ru.zfix27r.domain.repository.DictionaryRepository
import javax.inject.Inject

class DictionaryRepositoryImpl @Inject constructor(private val dao: DictionaryDao) :
    DictionaryRepository {
    override fun getDictionary(commonReqModel: CommonReqModel): Flow<DictionaryResModel> {
        return flow {
            val dictionary = dao.getDictionary(commonReqModel.id)
            if (dictionary == null) emit(DictionaryResModel.Fail(ErrorType.UNKNOWN_ERROR))
            else emit(dictionary.toDictionary())
        }.flowOn(Dispatchers.IO)
    }

    override fun getTopic(commonReqModel: CommonReqModel): Flow<TopicResModel> {
        return flow {
            val topic = dao.getTopic(commonReqModel.id)
            if (topic == null) emit(TopicResModel.Fail(ErrorType.UNKNOWN_ERROR))
            else emit(topic)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun addTopic(addTopicReqModel: AddTopicReqModel): Flow<CommonResModel?> {
        return flow {
            val result = dao.insert(addTopicReqModel)
            Log.e("dictionaryRepository add", result.toString())
            if (result == 1) emit(CommonResModel.Fail(ErrorType.UNKNOWN_ERROR))
            else emit(null)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun saveTopic(saveTopicReqModel: SaveTopicReqModel): Flow<CommonResModel?> {
        return flow {
            val result = dao.update(saveTopicReqModel)
            Log.e("dictionaryRepository save", result.toString())
            if (result == 1) emit(CommonResModel.Fail(ErrorType.UNKNOWN_ERROR))
            else emit(null)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun deleteTopic(commonReqModel: CommonReqModel): Flow<CommonResModel?> {
        return flow {
            val result = dao.delete(commonReqModel)
            Log.e("dictionaryRepository delete", result.toString())
            if (result == 1) emit(CommonResModel.Fail(ErrorType.UNKNOWN_ERROR))
            else emit(null)
        }.flowOn(Dispatchers.IO)
    }
}