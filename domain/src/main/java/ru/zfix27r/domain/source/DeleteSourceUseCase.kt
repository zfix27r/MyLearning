package ru.zfix27r.domain.source

import ru.zfix27r.domain.SourceRepository
import javax.inject.Inject

class DeleteSourceUseCase @Inject constructor(private val repository: SourceRepository) {
    fun execute(id: Int) = repository.deleteSource(id)
}