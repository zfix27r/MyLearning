package ru.zfix27r.domain.usecases

import ru.zfix27r.domain.model.SaveTopicReqModel
import ru.zfix27r.domain.repository.DictionaryRepository
import javax.inject.Inject

class SaveTopicUseCase @Inject constructor(private val repository: DictionaryRepository) {
    suspend fun execute(model: SaveTopicReqModel) = repository.saveTopic(model)
}