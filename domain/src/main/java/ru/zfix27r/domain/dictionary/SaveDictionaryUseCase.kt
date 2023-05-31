package ru.zfix27r.domain.dictionary

import ru.zfix27r.domain.DictionaryRepository
import javax.inject.Inject

class SaveDictionaryUseCase @Inject constructor(private val repository: DictionaryRepository) {
    fun execute(saveDictionaryRequestModel: SaveDictionaryRequestModel) =
        repository.saveDictionary(saveDictionaryRequestModel)
}