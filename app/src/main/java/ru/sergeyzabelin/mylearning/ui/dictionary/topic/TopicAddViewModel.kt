package ru.sergeyzabelin.mylearning.ui.dictionary.topic

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.sergeyzabelin.mylearning.data.model.db.Topic
import ru.sergeyzabelin.mylearning.domain.usecases.SaveTopicUseCase
import ru.sergeyzabelin.mylearning.ui.dictionary.common.InputStatus
import javax.inject.Inject


@HiltViewModel
class TopicAddViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val saveTopicUseCase: SaveTopicUseCase
) : ViewModel() {

    private val topicId: Long = savedStateHandle.get<Long>("topicId")!!

    var title: String = ""
        set(value) {
            checkInputTitle(value)
            if (inputTitleStatus == InputStatus.SUCCESS) field = value
        }

    private var _inputTitleStatus: InputStatus = InputStatus.EMPTY
    val inputTitleStatus
        get() = _inputTitleStatus

    var subTitle: String = ""
        set(value) {
            checkInputSubTitle(value)
            if (inputSubTitleStatus == InputStatus.SUCCESS) field = value
        }

    private var _inputSubTitleStatus: InputStatus = InputStatus.SUCCESS
    val inputSubTitleStatus
        get() = _inputSubTitleStatus

    fun add() = viewModelScope.launch {
        saveTopicUseCase.execute(
            Topic(
                id = 0,
                parentTopicId = topicId,
                title = title,
                subTitle = subTitle,
                isHasChild = false,
                counterQuote = 0
            )
        )
    }

    private fun checkInputTitle(title: String) {
        _inputTitleStatus =
            if (title.isEmpty()) InputStatus.EMPTY
            else InputStatus.SUCCESS
    }

    private fun checkInputSubTitle(subTitle: String) {
        _inputSubTitleStatus =
            if (subTitle.isEmpty()) InputStatus.EMPTY
            else InputStatus.SUCCESS
    }

    fun isInputCorrectly(): Boolean {
        return (InputStatus.SUCCESS == inputTitleStatus
                && (InputStatus.SUCCESS == inputSubTitleStatus
                || InputStatus.EMPTY == inputSubTitleStatus))
    }
}