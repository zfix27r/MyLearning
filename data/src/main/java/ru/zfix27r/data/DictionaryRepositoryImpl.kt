package ru.zfix27r.data

/*
class DictionaryRepositoryImpl @Inject constructor(private val dao: DictionaryDao) :
    DictionaryRepository {
    override fun getDictionary(commonReqModel: CommonReqModel): Flow<DictionaryResModel> =
        dao.getDictionary(commonReqModel.id).map { it.toDictionary() }

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
            // TODO удаление. Поиск всех вложенных тем, выдача как оповещение пользователю, после его подтверждения уже удаление всего пачкой.
*/
/*            val set = HashSet<Long>()
            while (true) {
                val result = dao.getTopic()
            }
            *//*


            val result = dao.delete(commonReqModel)
            if (result == 1) emit(ResponseModel(ResponseType.UNKNOWN_ERROR))
            else emit(ResponseModel(ResponseType.SUCCESS))
        }
}*/
