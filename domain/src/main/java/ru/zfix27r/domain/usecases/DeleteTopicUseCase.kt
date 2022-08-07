package ru.zfix27r.domain.usecases

import ru.zfix27r.domain.repository.TopicRepository
import javax.inject.Inject

class DeleteTopicUseCase @Inject constructor(private val repository: TopicRepository) {
    suspend fun execute(id: Long) = repository.delete(id)
}