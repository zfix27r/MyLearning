package ru.zfix27r.domain.editor.topic

import ru.zfix27r.domain.TopicRepository
import javax.inject.Inject

class DeleteTopicUseCase @Inject constructor(private val repository: TopicRepository) {
    fun execute(topicId: Int) = repository.deleteTopic(topicId)
}