package ru.zfix27r.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.zfix27r.data.local.db.ContentDao
import ru.zfix27r.domain.model.content.ContentReqModel
import ru.zfix27r.domain.model.content.ContentResModel
import ru.zfix27r.domain.repository.ContentRepository
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(private val dao: ContentDao) : ContentRepository {
    override fun getContent(contentReqModel: ContentReqModel): Flow<ContentResModel> {
        return flow {
            val result = dao.getContent(1)
            result.topic

            emit(ContentResModel.Fail(1))
        }.flowOn(Dispatchers.IO)
    }
}
