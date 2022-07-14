package ru.sergeyzabelin.mylearning.domain.usecases

import ru.sergeyzabelin.mylearning.data.DictionaryRepositoryImpl
import ru.sergeyzabelin.mylearning.data.model.db.Topic
import javax.inject.Inject

class AddTopicUseCase @Inject constructor(
    private val dictionaryRepository: DictionaryRepositoryImpl
) {
    suspend fun execute(topic: Topic) {
        dictionaryRepository.addTopic(topic)
    }
}