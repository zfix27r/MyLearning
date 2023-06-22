package ru.zfix27r.domain.question

import ru.zfix27r.domain.QuestionRepository
import javax.inject.Inject

class DeleteQuestionUseCase @Inject constructor(private val repository: QuestionRepository) {
    fun execute(id: Int) = repository.deleteQuestion(id)
}