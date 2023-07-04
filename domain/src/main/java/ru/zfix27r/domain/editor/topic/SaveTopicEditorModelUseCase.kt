package ru.zfix27r.domain.editor.topic

import ru.zfix27r.domain.TopicRepository
import javax.inject.Inject

class SaveTopicEditorModelUseCase @Inject constructor(private val repository: TopicRepository) {
    fun execute(saveTopic: SaveTopicEditorModel) = repository.saveTopicEditorModel(saveTopic)
}