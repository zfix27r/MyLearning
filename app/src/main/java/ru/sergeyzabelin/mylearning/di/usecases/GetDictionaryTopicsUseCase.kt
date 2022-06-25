package ru.sergeyzabelin.mylearning.di.usecases

import androidx.lifecycle.LiveData
import ru.sergeyzabelin.mylearning.data.DictionaryRepository
import ru.sergeyzabelin.mylearning.data.common.Resource
import ru.sergeyzabelin.mylearning.data.model.db.DictionaryData
import javax.inject.Inject

class GetDictionaryTopicsUseCase @Inject constructor(
    private val dictionaryRepository: DictionaryRepository
) {
    fun execute(id: Long): LiveData<Resource<DictionaryData>> {
        return dictionaryRepository.getTopicByParentId(id)
    }
}