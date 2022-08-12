package ru.zfix27r.domain.usecases

import ru.zfix27r.domain.model.content.ContentReqModel
import ru.zfix27r.domain.repository.ContentRepository
import javax.inject.Inject

class GetContentUseCase @Inject constructor(private val repository: ContentRepository) {
    fun execute(model: ContentReqModel) = repository.getContent(model)
}
