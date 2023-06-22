package ru.zfix27r.domain.topicEditor

import ru.zfix27r.domain.TopicEditorRepository
import javax.inject.Inject

class GetTopic4TopicEditorUseCase @Inject constructor(private val repository: TopicEditorRepository) {
    fun execute(id: Int) = repository.getTopic(id)
}