package ru.sergeyzabelin.mylearning.ui.dictionary.editor

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
import ru.sergeyzabelin.mylearning.ui.dictionary.DictionaryInputStatus
import javax.inject.Inject


@HiltViewModel
class DictionaryEditorTopicEditViewModel @Inject constructor(
    getTopicUseCase: GetTopicUseCase,
    private val saveTopicUseCase: SaveTopicUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val topicId: Long = savedStateHandle.get<Long>("topicId")!!

    private var title: String = ""
    private var _inputTitleStatus: DictionaryInputStatus = DictionaryInputStatus.HELPER_EQUAL
    val inputTitleStatus
        get() = _inputTitleStatus

    private var subTitle: String = ""
    private var _inputSubTitleStatus: DictionaryInputStatus = DictionaryInputStatus.SUCCESS
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
                isHasChild = topic.value?.data?.isHasChild ?: false // TODO присваивается в случае null значение с потолка, но по логике с null до save() не попасть
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

    private fun getStatusTitle(): DictionaryInputStatus {
        if (title == topic.value?.data?.title)
            return DictionaryInputStatus.HELPER_EQUAL
        if (title.isEmpty())
            return DictionaryInputStatus.ERROR_EMPTY

        return DictionaryInputStatus.SUCCESS
    }


    fun isAllInputCorrect(): Boolean {
        if (inputTitleStatus != DictionaryInputStatus.SUCCESS)
            return false
        if (inputSubTitleStatus != DictionaryInputStatus.SUCCESS)
            return false

        return true
    }
}