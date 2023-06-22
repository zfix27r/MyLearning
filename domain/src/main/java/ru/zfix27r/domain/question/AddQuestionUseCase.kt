package ru.zfix27r.domain.question

import ru.zfix27r.domain.QuestionRepository
import javax.inject.Inject

class AddQuestionUseCase @Inject constructor(private val repository: QuestionRepository) {
    fun execute(addQuestionModel: AddQuestionModel) = repository.addQuestion(addQuestionModel)
}