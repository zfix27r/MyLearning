package ru.zfix27r.domain.usecases

import ru.zfix27r.domain.model.topic.TopicReqModel
import ru.zfix27r.domain.repository.TopicRepository
import javax.inject.Inject

class GetTopicByIdUseCase @Inject constructor(private val repository: TopicRepository) {
    fun execute(reqModel: TopicReqModel) = repository.getTopicById(reqModel)
}