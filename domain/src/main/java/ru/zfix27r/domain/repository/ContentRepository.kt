package ru.zfix27r.domain.repository

import androidx.lifecycle.LiveData
import ru.zfix27r.data.common.Resource
import ru.zfix27r.data.model.db.Content

interface ContentRepository {
    fun getContent(id: Long): LiveData<Resource<Content>>
}