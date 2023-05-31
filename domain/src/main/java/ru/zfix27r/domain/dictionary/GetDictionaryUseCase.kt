package ru.zfix27r.domain.dictionary

import ru.zfix27r.domain.DictionaryRepository
import javax.inject.Inject

class GetDictionaryUseCase @Inject constructor(private val repository: DictionaryRepository) {
    fun execute(id: Int) = repository.getDictionary(id)
}