package ru.sergeyzabelin.mylearning.ui.dictionary

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.sergeyzabelin.mylearning.data.common.Resource
import ru.sergeyzabelin.mylearning.data.model.db.Topic
import ru.sergeyzabelin.mylearning.domain.usecases.GetTopicUseCase
import ru.sergeyzabelin.mylearning.domain.usecases.SaveTopicUseCase
import ru.sergeyzabelin.mylearning.ui.dictionary.common.InputStatus.EMPTY
import ru.sergeyzabelin.mylearning.ui.dictionary.common.InputStatus.SUCCESS
import javax.inject.Inject


@HiltViewModel
class TopicEditorViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getTopicUseCase: GetTopicUseCase,
    private val saveTopicUseCase: SaveTopicUseCase
) : ViewModel() {

    private val topicId: Long = savedStateHandle.get<Long>(TOPIC_ID) ?: 0
    private val parentTopicId: Long = savedStateHandle.get<Long>(PARENT_TOPIC_ID) ?: 0

    val data: LiveData<Resource<Topic>>? =
        if (topicId > 0) getTopicUseCase.execute(topicId) else null

    var title: String = ""
        set(value) {
            checkInput(value)
            if (inputTitleStatus == SUCCESS) field = value
        }

    private var _inputTitleStatus = EMPTY
    val inputTitleStatus
        get() = _inputTitleStatus

    var subTitle: String = ""
        set(value) {
            checkInput(value)
            if (inputSubTitleStatus == SUCCESS) field = value
        }

    private var _inputSubTitleStatus = SUCCESS
    val inputSubTitleStatus
        get() = _inputSubTitleStatus

    init {

    }


    fun isInputCorrectly(): Boolean {
        return (SUCCESS == inputTitleStatus)
    }

    fun save() = viewModelScope.launch {
        val topic = Topic(
            id = data?.value?.data?.id ?: 0,
            parentTopicId = parentTopicId,
            title = title,
            subTitle = subTitle,
            isHasChild = data?.value?.data?.isHasChild ?: false,
            counterQuote = data?.value?.data?.counterQuote ?: 0
        )
        saveTopicUseCase.execute(topic)
    }

    private fun checkInput(s: String) {
        _inputTitleStatus =
            if (s.isEmpty()) EMPTY
            else SUCCESS
    }

    companion object {
        const val TOPIC_ID = "topicId"
        const val PARENT_TOPIC_ID = "parentTopicId"
    }
}