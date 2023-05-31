package ru.zfix27r.domain.dictionary

import ru.zfix27r.domain.DictionaryRepository
import javax.inject.Inject

class AddDictionaryUseCase @Inject constructor(private val repository: DictionaryRepository) {
    fun execute(addDictionaryRequestModel: AddDictionaryRequestModel) =
        repository.addDictionary(addDictionaryRequestModel)
}
