package ru.zfix27r.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.zfix27r.domain.model.CommonReqModel
import ru.zfix27r.domain.model.content.ContentResModel

interface ContentRepository {
    fun getContent(commonReqModel: CommonReqModel): Flow<ContentResModel>
}
