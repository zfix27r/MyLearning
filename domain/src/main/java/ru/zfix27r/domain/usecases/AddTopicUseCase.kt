package ru.zfix27r.domain.usecases

import ru.zfix27r.domain.model.topic.AddTopicModel
import ru.zfix27r.domain.repository.TopicRepository
import javax.inject.Inject

class AddTopicUseCase @Inject constructor(private val repository: TopicRepository) {
    suspend fun execute(model: AddTopicModel) = repository.add(model)
}