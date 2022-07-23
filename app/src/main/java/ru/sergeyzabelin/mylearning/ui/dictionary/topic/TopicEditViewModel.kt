package ru.sergeyzabelin.mylearning.ui.dictionary.topic

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
import ru.sergeyzabelin.mylearning.ui.dictionary.common.InputStatus
import javax.inject.Inject


@HiltViewModel
class TopicEditViewModel @Inject constructor(
    getTopicUseCase: GetTopicUseCase,
    private val saveTopicUseCase: SaveTopicUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val topicId: Long = savedStateHandle.get<Long>("topicId")!!

    private var title: String = ""
    private var _inputTitleStatus: InputStatus = InputStatus.NOT_CHANGED
    val inputTitleStatus
        get() = _inputTitleStatus

    private var subTitle: String = ""
    private var _inputSubTitleStatus: InputStatus = InputStatus.SUCCESS
    val inputSubTitleStatus
        get() = _inputSubTitleStatus

    val topic: LiveData<Resource<Topic>> = getTopicUseCase.execute(topicId)

    fun save() = viewModelScope.launch {
        saveTopicUseCase.execute(
            Topic(
                id = 0,
                parentTopicId = topicId,
                title = title,
                subTitle = subTitle,
                isHasChild = topic.value?.data?.isHasChild ?: false, // TODO присваивается в случае null значение с потолка, но по логике с null до save() не попасть
                counterQuote = topic.value?.data?.counterQuote ?: 0
            )
        )
    }

    fun checkInputTitle(title: String) {
        this.title = title
        _inputTitleStatus = getStatusTitle()
    }

    fun checkInputSubTitle(subTitle: String) {
        this.subTitle = subTitle
    }

    private fun getStatusTitle(): InputStatus {
        if (title == topic.value?.data?.title)
            return InputStatus.NOT_CHANGED
        if (title.isEmpty())
            return InputStatus.EMPTY

        return InputStatus.SUCCESS
    }


    fun isAllInputCorrect(): Boolean {
        if (inputTitleStatus != InputStatus.SUCCESS)
            return false
        if (inputSubTitleStatus != InputStatus.SUCCESS)
            return false

        return true
    }
}