package ru.zfix27r.domain.usecases

import ru.zfix27r.domain.model.AddTopicReqModel
import ru.zfix27r.domain.repository.DictionaryRepository
import javax.inject.Inject

class AddTopicUseCase @Inject constructor(private val repository: DictionaryRepository) {
    suspend fun execute(model: AddTopicReqModel) = repository.addTopic(model)
}
