package ru.sergeyzabelin.mylearning.domain.repository

import androidx.lifecycle.LiveData
import ru.sergeyzabelin.mylearning.data.common.Resource
import ru.sergeyzabelin.mylearning.data.model.db.*

interface DictionaryRepository {

    fun getDictionaryBy(id: Long): LiveData<Resource<Dictionary>>

    fun getTopicBy(id: Long): LiveData<Resource<Topic>>

    fun getContentBy(id: Long): LiveData<Resource<Content>>

    suspend fun save(quote: Quote)
    suspend fun save(topic: Topic)
    suspend fun save(source: Source)
    suspend fun save(question: Question)

    suspend fun delete(quote: Quote)
    suspend fun delete(topic: Topic)
    suspend fun delete(source: Source)
    suspend fun delete(question: Question)
}