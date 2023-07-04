package ru.zfix27r.domain.editor.topic.parent

import ru.zfix27r.domain.TopicRepository
import javax.inject.Inject

class GetTopicEditorParentModelByTopicIdUseCase @Inject constructor(private val repository: TopicRepository) {
    fun execute(topicId: Int) = repository.getTopicEditorParentModelByTopicId(topicId)
}