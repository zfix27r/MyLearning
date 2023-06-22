package ru.zfix27r.domain.topicEditor

import ru.zfix27r.domain.TopicEditorRepository
import javax.inject.Inject

class GetTopicsByTopicId4TopicEditorUseCase @Inject constructor(
    private val repository: TopicEditorRepository
) {
    fun execute(topicId: Int, topicIdSelf: Int) =
        repository.getTopicsByTopicId(topicId, topicIdSelf)
}