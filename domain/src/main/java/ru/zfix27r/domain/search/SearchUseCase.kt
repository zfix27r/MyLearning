package ru.zfix27r.domain.search

import ru.zfix27r.domain.MainRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repository: MainRepository) {
    fun execute(string: String) = repository.search(string)
}