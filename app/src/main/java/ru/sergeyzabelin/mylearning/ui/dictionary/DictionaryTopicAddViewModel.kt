package ru.sergeyzabelin.mylearning.ui.dictionary

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.sergeyzabelin.mylearning.domain.model.SaveTopicModel
import ru.sergeyzabelin.mylearning.domain.usecases.AddDictionaryTopicUseCase
import javax.inject.Inject


@HiltViewModel
class DictionaryTopicAddViewModel @Inject constructor(
    private val addDictionaryTopicUseCase: AddDictionaryTopicUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val topicId: Long = savedStateHandle.get<Long>("topicId")!!
    private val saveTopicModel: SaveTopicModel = SaveTopicModel()

    private var _inputTitleStatus: DictionaryInputStatus = DictionaryInputStatus.HELPER_EQUAL
    val inputTitleStatus
        get() = _inputTitleStatus

    private var _inputLabelStatus: DictionaryInputStatus = DictionaryInputStatus.SUCCESS
    val inputLabelStatus
        get() = _inputLabelStatus

    fun add() = viewModelScope.launch {
        saveTopicModel.topicParentId = topicId

        addDictionaryTopicUseCase.execute(saveTopicModel)
    }

    fun checkInputTitle(title: String) {
        saveTopicModel.title = title
        _inputTitleStatus = getStatusTitle()
    }

    fun checkInputLabel(label: String) {
        saveTopicModel.label = label
    }

    private fun getStatusTitle(): DictionaryInputStatus {
        if (saveTopicModel.title.isEmpty())
            return DictionaryInputStatus.ERROR_EMPTY

        return DictionaryInputStatus.SUCCESS
    }


    fun isAllInputCorrect(): Boolean {
        if (inputTitleStatus != DictionaryInputStatus.SUCCESS)
            return false
        if (inputLabelStatus != DictionaryInputStatus.SUCCESS)
            return false

        return true
    }
}