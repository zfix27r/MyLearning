package ru.zfix27r.data

import kotlinx.coroutines.flow.Flow
import ru.zfix27r.data.local.dao.ContentDao
import ru.zfix27r.domain.model.CommonReqModel
import ru.zfix27r.domain.model.content.ContentResModel
import ru.zfix27r.domain.ContentRepository
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(private val dao: ContentDao) : ContentRepository {
    override fun getContent(commonReqModel: CommonReqModel): Flow<ContentResModel> = TODO()
        //dao.getContent(commonReqModel.id).map { it.toContent() }
}