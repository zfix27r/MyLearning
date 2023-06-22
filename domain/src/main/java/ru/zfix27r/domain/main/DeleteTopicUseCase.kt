package ru.zfix27r.domain.main

import ru.zfix27r.domain.MainRepository
import javax.inject.Inject

class DeleteTopicUseCase @Inject constructor(private val repository: MainRepository) {
    fun execute(id: Int) = repository.deleteTopic(id)
}
