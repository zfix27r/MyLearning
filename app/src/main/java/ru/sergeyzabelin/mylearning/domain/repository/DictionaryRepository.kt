package ru.sergeyzabelin.mylearning.domain.repository

import androidx.lifecycle.LiveData
import ru.sergeyzabelin.mylearning.data.common.Resource
import ru.sergeyzabelin.mylearning.data.model.db.Dictionary
import ru.sergeyzabelin.mylearning.data.model.db.Quote
import ru.sergeyzabelin.mylearning.data.model.db.Topic

interface DictionaryRepository {

    fun getDictionaryBy(id: Long): LiveData<Resource<Dictionary>>

    fun getTopicBy(id: Long): LiveData<Resource<Topic>>

    suspend fun addTopic(topic: Topic)

    suspend fun saveTopic(topic: Topic)

    suspend fun deleteTopic(topic: Topic)

    suspend fun saveQuote(article: Quote)
}