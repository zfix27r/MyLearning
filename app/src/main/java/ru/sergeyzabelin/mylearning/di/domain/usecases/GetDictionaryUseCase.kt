package ru.sergeyzabelin.mylearning.di.domain.usecases

import androidx.lifecycle.LiveData
import ru.sergeyzabelin.mylearning.data.DictionaryRepository
import ru.sergeyzabelin.mylearning.data.Resource
import ru.sergeyzabelin.mylearning.data.model.db.Topic
import javax.inject.Inject

class GetDictionaryUseCase @Inject constructor(
    private val dictionaryRepository: DictionaryRepository
) {
    fun execute(topicId: Long): LiveData<Resource<List<Topic>>> {
        return dictionaryRepository.getTopicByParentId(topicId)
    }
}