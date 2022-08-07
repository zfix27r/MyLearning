package ru.zfix27r.domain.usecases

import androidx.lifecycle.LiveData
import ru.zfix27r.data.DictionaryRepositoryImpl
import ru.zfix27r.data.common.Resource
import ru.zfix27r.data.model.db.Dictionary
import javax.inject.Inject

class GetDictionaryUseCase @Inject constructor(
    private val dictionaryRepository: DictionaryRepositoryImpl
) {
    fun execute(id: Long): LiveData<Resource<Dictionary>> {
        return dictionaryRepository.getDictionary(id)
    }
}