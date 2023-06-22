package ru.zfix27r.domain.topicEditor

import ru.zfix27r.domain.TopicEditorRepository
import javax.inject.Inject

class SaveTopic4TopicEditorUseCase @Inject constructor(private val repository: TopicEditorRepository) {
    fun execute(saveTopicModel: SaveTopic4TopicEditorModel) = repository.saveTopic(saveTopicModel)
}