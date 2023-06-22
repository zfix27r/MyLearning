package ru.zfix27r.domain.source

import ru.zfix27r.domain.SourceRepository
import javax.inject.Inject

class SaveSourceUseCase @Inject constructor(private val repository: SourceRepository) {
    fun execute(saveSourceModel: SaveSourceModel) = repository.saveSource(saveSourceModel)
}