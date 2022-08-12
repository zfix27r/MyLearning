package ru.zfix27r.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.zfix27r.domain.model.content.ContentReqModel
import ru.zfix27r.domain.model.content.ContentResModel

interface ContentRepository {
    fun getContent(contentReqModel: ContentReqModel): Flow<ContentResModel>
}
