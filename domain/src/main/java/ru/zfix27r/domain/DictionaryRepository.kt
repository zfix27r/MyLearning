package ru.zfix27r.domain

import kotlinx.coroutines.flow.Flow
import ru.zfix27r.domain.dictionary.AddDictionaryRequestModel
import ru.zfix27r.domain.dictionary.GetDictionaryResponseModel
import ru.zfix27r.domain.dictionary.SaveDictionaryRequestModel

interface DictionaryRepository {
    fun getDictionary(id: Int): Flow<GetDictionaryResponseModel>
    fun addDictionary(addDictionaryRequestModel: AddDictionaryRequestModel): Flow<Unit>
    fun saveDictionary(saveTopicReqModel: SaveDictionaryRequestModel): Flow<Unit>
    fun deleteDictionary(id: Int): Flow<Unit>
}