package ru.zfix27r.domain

import kotlinx.coroutines.flow.Flow
import ru.zfix27r.domain.question.AddQuestionModel
import ru.zfix27r.domain.question.SaveQuestionModel

interface QuestionRepository {
    fun addQuestion(addQuestionModel: AddQuestionModel): Flow<Unit>
    fun saveQuestion(saveQuestionModel: SaveQuestionModel): Flow<Unit>
    fun deleteQuestion(id: Int): Flow<Unit>
}