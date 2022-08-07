package ru.zfix27r.domain.usecases

import ru.zfix27r.domain.model.topic.SaveTopicModel
import ru.zfix27r.domain.repository.TopicRepository
import javax.inject.Inject

class SaveTopicUseCase @Inject constructor(private val repository: TopicRepository) {
    suspend fun execute(model: SaveTopicModel) = repository.save(model)
}