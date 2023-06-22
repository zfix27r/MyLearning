package ru.zfix27r.domain.topicEditor

import ru.zfix27r.domain.TopicEditorRepository
import javax.inject.Inject

class GetTopicsByTopicParentId4TopicEditorUseCase @Inject constructor(
    private val repository: TopicEditorRepository
) {
    fun execute(topicId: Int, topicIdSelf: Int) =
        repository.getTopicsByTopicParentId(topicId, topicIdSelf)
}