package ru.sergeyzabelin.mylearning.domain.repository

import androidx.lifecycle.LiveData
import ru.sergeyzabelin.mylearning.data.common.Resource
import ru.sergeyzabelin.mylearning.data.model.db.*

interface DictionaryRepository {

    fun getDictionaryBy(id: Long): LiveData<Resource<Dictionary>>

    fun getTopicBy(id: Long): LiveData<Resource<Topic>>

    fun getContentBy(id: Long): LiveData<Resource<Content>>

    suspend fun insert(quote: Quote): Long
    suspend fun insert(topic: Topic): Long
    suspend fun insert(source: Source): Long
    suspend fun insert(question: Question): Long

    suspend fun update(quote: Quote): Int
    suspend fun update(topic: Topic): Int
    suspend fun update(source: Source): Int
    suspend fun update(question: Question): Int

    suspend fun delete(quote: Quote): Int
    suspend fun delete(topic: Topic): Int
    suspend fun delete(source: Source): Int
    suspend fun delete(question: Question): Int
}