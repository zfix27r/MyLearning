package ru.zfix27r.domain.dictionary

import ru.zfix27r.domain.DictionaryRepository
import javax.inject.Inject

class DeleteDictionaryUseCase @Inject constructor(private val repository: DictionaryRepository) {
    fun execute(id: Int) = repository.deleteDictionary(id)
}
