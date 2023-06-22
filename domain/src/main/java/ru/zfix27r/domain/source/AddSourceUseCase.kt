package ru.zfix27r.domain.source

import ru.zfix27r.domain.SourceRepository
import javax.inject.Inject

class AddSourceUseCase @Inject constructor(private val repository: SourceRepository) {
    fun execute(addSourceModel: AddSourceModel) = repository.addSource(addSourceModel)
}