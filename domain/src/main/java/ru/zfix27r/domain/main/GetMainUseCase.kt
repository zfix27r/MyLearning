package ru.zfix27r.domain.main

import ru.zfix27r.domain.MainRepository
import javax.inject.Inject

class GetMainUseCase @Inject constructor(
    val repository: MainRepository
) {
    fun execute(topicId: Int) = repository.getMain(topicId)
}