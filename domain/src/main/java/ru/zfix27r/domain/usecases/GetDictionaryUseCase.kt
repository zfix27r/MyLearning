package ru.zfix27r.domain.usecases

import ru.zfix27r.domain.model.CommonReqModel
import ru.zfix27r.domain.repository.DictionaryRepository
import javax.inject.Inject

class GetDictionaryUseCase @Inject constructor(private val repository: DictionaryRepository) {
    fun execute(model: CommonReqModel) = repository.getDictionary(model)
}