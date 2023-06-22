package ru.zfix27r.domain.question

import ru.zfix27r.domain.QuestionRepository
import javax.inject.Inject

class SaveQuestionUseCase @Inject constructor(private val repository: QuestionRepository) {
    fun execute(saveQuestionModel: SaveQuestionModel) = repository.saveQuestion(saveQuestionModel)
}