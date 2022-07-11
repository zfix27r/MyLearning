package ru.sergeyzabelin.mylearning.domain.usecases

import ru.sergeyzabelin.mylearning.data.DictionaryRepositoryImpl
import ru.sergeyzabelin.mylearning.domain.model.SaveTopicModel
import javax.inject.Inject

class AddDictionaryTopicUseCase @Inject constructor(
    private val dictionaryRepository: DictionaryRepositoryImpl
) {
    suspend fun execute(saveTopicModel: SaveTopicModel) {
        dictionaryRepository.addDictionaryTopic(saveTopicModel)
    }
}