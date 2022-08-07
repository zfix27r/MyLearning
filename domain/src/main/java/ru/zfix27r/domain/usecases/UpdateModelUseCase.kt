package ru.zfix27r.domain.usecases

import javax.inject.Inject

class UpdateModelUseCase @Inject constructor(
    private val updater: ModelUpdaterRepositoryImpl
) {
    suspend fun <T> execute(model: T): Int {
        return updater.update(model)
    }
}