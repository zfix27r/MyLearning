package ru.sergeyzabelin.mylearning.ui.dictionary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.sergeyzabelin.mylearning.ui.BaseViewModel
import ru.zfix27r.domain.model.CommonReqModel
import ru.zfix27r.domain.model.DictionaryDataModel
import ru.zfix27r.domain.model.DictionaryFailModel
import ru.zfix27r.domain.model.common.ResponseType
import ru.zfix27r.domain.usecases.DeleteTopicUseCase
import ru.zfix27r.domain.usecases.GetDictionaryUseCase
import javax.inject.Inject

@HiltViewModel
class DictionaryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getDictionaryUseCase: GetDictionaryUseCase,
    private val deleteTopicUseCase: DeleteTopicUseCase
) : BaseViewModel() {
    private val topicId: Long = savedStateHandle[TOPIC_ID] ?: 0

    private val _topic: MutableLiveData<Topic> = MutableLiveData()
    val topic: LiveData<Topic> = _topic

    private val _topics: MutableLiveData<List<Topics>> = MutableLiveData()
    val topics: LiveData<List<Topics>> = _topics

    init {
        loadDictionary()
    }

    private fun loadDictionary() = viewModelScope.launch(Dispatchers.IO) {
        getDictionaryUseCase.execute(CommonReqModel(topicId)).collect {
            when (it) {
                is DictionaryDataModel -> {
                    _topic.postValue(it.topic)

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
        const val TOPIC_ID = "topicId"
    }
}