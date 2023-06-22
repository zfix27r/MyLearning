package ru.zfix27r.domain.main

import ru.zfix27r.domain.MainRepository
import javax.inject.Inject

class GetTopicsByParentIdUseCase @Inject constructor(private val repository: MainRepository) {
    fun execute(topicParentId: Int) = repository.getTopicsByParentId(topicParentId)
}