package ru.sergeyzabelin.mylearning.ui.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.sergeyzabelin.mylearning.ui.BaseViewModel
import ru.zfix27r.domain.model.CommonReqModel
import ru.zfix27r.domain.model.common.ResponseType
import ru.zfix27r.domain.model.content.ContentDataModel
import ru.zfix27r.domain.model.content.ContentFailModel
import ru.zfix27r.domain.usecases.GetContentUseCase
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getContentUseCase: GetContentUseCase
) : BaseViewModel() {
    private val topicId: Long = savedStateHandle.get<Long>(TOPIC_ID) ?: 0

    init {
        if (topicId == 0L) _response.value = ResponseType.INIT_DATA_EMPTY
    }

    private val _content = MutableLiveData<ContentDataModel>()
    val content: LiveData<ContentDataModel> = _content

    init {
        loadContent()
    }

    private fun loadContent() = viewModelScope.launch(Dispatchers.IO) {
        getContentUseCase.execute(CommonReqModel(topicId)).collect {
            when (it) {
                is ContentDataModel -> {
                    _content.postValue(it)
                }
                is ContentFailModel -> {
                    _response.postValue(it.responseType)
                }
            }
        }
    }


    companion object {
        const val TOPIC_ID = "topicId"
    }
}