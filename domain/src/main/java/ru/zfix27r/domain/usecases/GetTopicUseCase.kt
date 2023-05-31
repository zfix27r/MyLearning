package ru.zfix27r.domain.usecases

import ru.zfix27r.domain.model.CommonReqModel
import ru.zfix27r.domain.DictionaryRepository
import javax.inject.Inject

class GetTopicUseCase @Inject constructor(private val repository: DictionaryRepository) {
    suspend fun execute(model: CommonReqModel) = repository.getTopic(model)
}