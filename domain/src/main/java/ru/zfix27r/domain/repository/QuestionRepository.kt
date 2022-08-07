package ru.zfix27r.domain.repository

import ru.zfix27r.data.model.db.Question

interface QuestionRepository {
    suspend fun insert(question: Question): Long
    suspend fun update(question: Question): Int
    suspend fun delete(question: Question): Int
}