package ru.zfix27r.domain.activity

import ru.zfix27r.domain.ActivityRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repository: ActivityRepository) {
    fun execute(query: String) = repository.search(query)
}