package ru.zfix27r.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.zfix27r.data.local.db.ContentDao
import ru.zfix27r.domain.model.CommonReqModel
import ru.zfix27r.domain.model.content.ContentResModel
import ru.zfix27r.domain.repository.ContentRepository
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(private val dao: ContentDao) : ContentRepository {
    override fun getContent(commonReqModel: CommonReqModel): Flow<ContentResModel> =
        dao.getContent(commonReqModel.id).map { it.toContent() }
}
