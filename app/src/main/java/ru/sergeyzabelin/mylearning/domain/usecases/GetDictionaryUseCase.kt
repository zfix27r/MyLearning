package ru.sergeyzabelin.mylearning.domain.usecases

import androidx.lifecycle.LiveData
import ru.sergeyzabelin.mylearning.data.DictionaryRepositoryImpl
import ru.sergeyzabelin.mylearning.data.common.Resource
import ru.sergeyzabelin.mylearning.data.model.db.Dictionary
import javax.inject.Inject

class GetDictionaryUseCase @Inject constructor(
    private val dictionaryRepository: DictionaryRepositoryImpl
) {
    fun execute(id: Long): LiveData<Resource<Dictionary>> {
        return dictionaryRepository.getDictionaryBy(id)
    }
}