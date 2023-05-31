package ru.zfix27r.domain.usecases

import ru.zfix27r.domain.model.CommonReqModel
import ru.zfix27r.domain.ContentRepository
import javax.inject.Inject

class GetContentUseCase @Inject constructor(private val repository: ContentRepository) {
    fun execute(model: CommonReqModel) = repository.getContent(model)
}
