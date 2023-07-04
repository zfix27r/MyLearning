package ru.zfix27r.domain.main

import ru.zfix27r.domain.MainRepository
import javax.inject.Inject

class GetMainQuotesUseCase @Inject constructor(private val repository: MainRepository) {
    fun execute(topicId: Int) = repository.getQuotes(topicId)
}