package ru.zfix27r.domain.main

import ru.zfix27r.domain.MainRepository
import javax.inject.Inject

class GetMainModelsUseCase @Inject constructor(val repository: MainRepository) {
    fun execute() = repository.getMainModels()
}