package ru.sergeyzabelin.mylearning.ui.dictionary.editor

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.sergeyzabelin.mylearning.data.model.db.Topic
import ru.sergeyzabelin.mylearning.domain.usecases.AddTopicUseCase
import ru.sergeyzabelin.mylearning.ui.dictionary.DictionaryInputStatus
import javax.inject.Inject


@HiltViewModel
class DictionaryEditorTopicAddViewModel @Inject constructor(
    private val addDictionaryTopicUseCase: AddTopicUseCase,
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

    fun add() = viewModelScope.launch {
        addDictionaryTopicUseCase.execute(
            Topic(
                id = 0,
                parentTopicId = topicId,
                title = title,
                subTitle = subTitle,
                isHasChild = false
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