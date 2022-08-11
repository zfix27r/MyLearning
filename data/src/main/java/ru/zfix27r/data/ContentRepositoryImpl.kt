package ru.zfix27r.data

/*
class ContentRepositoryImpl @Inject constructor(private val dao: ContentDao) : ContentRepository {
    override fun getContent(contentReqModel: ContentReqModel): Flow<ContentResModel> {
        return flow {
*/
/*            val result = dao.getContent(1)
            result.topic*//*

            emit(ContentResModel.Fail(1))
        }.flowOn(Dispatchers.IO)
    }
}*/
