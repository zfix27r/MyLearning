package ru.zfix27r.mylearning.ui.dictionary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.zfix27r.domain.dictionary.DeleteDictionaryUseCase
import ru.zfix27r.domain.dictionary.GetDictionaryUseCase
import ru.zfix27r.mylearning.ui.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class DictionaryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getDictionaryUseCase: GetDictionaryUseCase,
    private val deleteDictionaryUseCase: DeleteDictionaryUseCase,
) : BaseViewModel() {
    private val dictionaryId: Int = savedStateHandle[DICTIONARY_ID] ?: DICTIONARY_ID_DEFAULT

    private val _dictionary: MutableLiveData<List<DictionaryUIModel>> = MutableLiveData()
    val topic: LiveData<List<DictionaryUIModel>> = _dictionary

    init {
        loadDictionary()
    }

    private fun loadDictionary() = viewModelScope.launch(Dispatchers.IO) {
        getDictionaryUseCase.execute(CommonReqModel(dictionaryId)).collect {
            when (it) {
                is DictionaryDataModel -> {
                    _dictionary.postValue(it.topic)

                    if (it.topics.isEmpty()) _response.postValue(ResponseType.RESPONSE_EMPTY)
                    else _topics.postValue(it.topics)
                }
                is DictionaryFailModel -> {
                    _response.postValue(it.errorType)
                }
            }
        }
    }

    fun delete(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteTopicUseCase.execute(CommonReqModel(id)).collect {
                _response.postValue(it.responseType)
            }
        }
    }

    companion object {
        const val DICTIONARY_ID_DEFAULT = 1
        const val DICTIONARY_ID = "dictionary_id"
    }
}