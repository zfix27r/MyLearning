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
import ru.zfix27r.domain.model.DictionaryResModel
import ru.zfix27r.domain.model.ResponseModel
import ru.zfix27r.domain.usecases.GetDictionaryUseCase
import javax.inject.Inject

@HiltViewModel
class DictionaryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getDictionaryUseCase: GetDictionaryUseCase
/*    private val deleteTopicUseCase: DeleteTopicUseCase*/
) : BaseViewModel() {
    private val topicId: Long = savedStateHandle[TOPIC_ID] ?: 0

    private val _topic: MutableLiveData<DictionaryResModel.Data> = MutableLiveData()
    val topic: LiveData<DictionaryResModel.Data> = _topic

    fun loadTopic() = viewModelScope.launch(Dispatchers.IO) {
        getDictionaryUseCase.execute(CommonReqModel(topicId)).collect {
            when (it) {
                is DictionaryResModel.Data -> {
                    _topic.postValue(it)
                }
                is DictionaryResModel.Fail -> {
                    _result.postValue(ResponseModel(it.errorType))
                }
            }
        }
    }

    /*    fun delete(model: DeleteTopicReqModel) {
            viewModelScope.launch(Dispatchers.IO) {
                deleteTopicUseCase.execute(model)
            }
        }*/
    companion object {
        const val TOPIC_ID = "topicId"
    }
}