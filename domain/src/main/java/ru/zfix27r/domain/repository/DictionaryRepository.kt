package ru.zfix27r.domain.repository

import androidx.lifecycle.LiveData
import ru.zfix27r.data.common.Resource
import ru.zfix27r.data.model.db.Dictionary

interface DictionaryRepository {
    fun getDictionary(id: Long): LiveData<Resource<Dictionary>>
}