package ru.sergeyzabelin.mylearning.ui.dictionary

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.sergeyzabelin.mylearning.data.common.Resource
import ru.sergeyzabelin.mylearning.data.model.db.Topic
import ru.sergeyzabelin.mylearning.domain.model.SaveTopicModel
import ru.sergeyzabelin.mylearning.domain.usecases.GetDictionarySaveTopicModelUseCase
import ru.sergeyzabelin.mylearning.domain.usecases.SaveDictionaryTopicUseCase
import javax.inject.Inject


@HiltViewModel
class DictionaryTopicEditViewModel @Inject constructor(
    getDictionaryTopicUseCase: GetDictionarySaveTopicModelUseCase,
    private val saveDictionaryTopicUseCase: SaveDictionaryTopicUseCase,
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

    val topic: LiveData<Resource<Topic>> = getDictionaryTopicUseCase.execute(topicId)

    fun save() = viewModelScope.launch {
        topic.value?.data?.let {
            if (saveTopicModel.label.isEmpty())
                saveTopicModel.label = it.label
            saveTopicModel.id = it.id
            saveTopicModel.topicParentId = it.parentTopicId
        }

        saveDictionaryTopicUseCase.execute(saveTopicModel)
    }

    fun checkInputTitle(title: String) {
        saveTopicModel.title = title
        _inputTitleStatus = getStatusTitle()
    }

    fun checkInputLabel(label: String) {
        saveTopicModel.label = label
    }

    private fun getStatusTitle(): DictionaryInputStatus {
        if (saveTopicModel.title == topic.value?.data?.title)
            return DictionaryInputStatus.HELPER_EQUAL
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