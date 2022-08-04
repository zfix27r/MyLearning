package ru.sergeyzabelin.mylearning.domain.usecases

import ru.sergeyzabelin.mylearning.data.DictionaryRepositoryImpl
import ru.sergeyzabelin.mylearning.data.model.db.Topic
import javax.inject.Inject

class SaveTopicUseCase @Inject constructor(
    private val dictionaryRepository: DictionaryRepositoryImpl
) {
    suspend fun execute(topic: Topic): Long {
        return if (topic.id > 0) dictionaryRepository.update(topic).toLong()
        else dictionaryRepository.insert(topic)
    }
}