package ru.sergeyzabelin.mylearning.ui.dictionary

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.sergeyzabelin.mylearning.ui.BaseViewModel
import ru.sergeyzabelin.mylearning.ui.common.InputStatus
import ru.sergeyzabelin.mylearning.ui.common.InputStatus.EMPTY
import ru.sergeyzabelin.mylearning.ui.common.InputStatus.SUCCESS
import ru.zfix27r.domain.model.*
import ru.zfix27r.domain.usecases.AddTopicUseCase
import ru.zfix27r.domain.usecases.GetTopicUseCase
import ru.zfix27r.domain.usecases.SaveTopicUseCase
import javax.inject.Inject

@HiltViewModel
class TopicEditorViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getTopicUseCase: GetTopicUseCase,
    private val addTopicUseCase: AddTopicUseCase,
    private val saveTopicUseCase: SaveTopicUseCase
) : BaseViewModel() {

    val topic: MutableLiveData<EditableTopicModel> = MutableLiveData(
        EditableTopicModel(
            id = savedStateHandle.get<Long>(TOPIC_ID) ?: 0,
            parentId = savedStateHandle.get<Long>(PARENT_ID) ?: 0
        )
    )

    fun isSaveMode(): Boolean {
        topic.value?.let { return it.id > 0 }
        return false
    }

    fun loadTopic(): Boolean {
        topic.value?.let { if (it.id < 1) return false }

        viewModelScope.launch(Dispatchers.IO) {
            topic.value?.let { topic ->
                getTopicUseCase.execute(CommonReqModel(topic.id)).collect {
                    when (it) {
                        is TopicResModel.Data -> {
                            topic.title = it.title
                            topic.subtitle = it.subtitle
                        }
                        is TopicResModel.Fail -> {
                            _result.postValue(ResponseModel(it.errorType))
                        }
                    }
                }
            }
        }
        return true
    }

    fun tryDone(): Boolean {
        if (isDoneInProgress.value == false && isInputConditionsCorrectly()) {
            isDoneInProgress.value = true
            viewModelScope.launch {
                withContext(Dispatchers.IO) { if (isSaveMode()) save() else add() }
                isDoneInProgress.value = false
            }
            return true
        }
        return false
    }

    private suspend fun save() {
        topic.value?.let { topic ->
            val saveModel = SaveTopicReqModel(
                id = topic.id,
                title = topic.title,
                subtitle = topic.subtitle,
            )

            saveTopicUseCase.execute(saveModel).collect { _result.postValue(it) }
        }
    }

    private suspend fun add() {
        topic.value?.let { topic ->
            val addModel = AddTopicReqModel(
                parentId = topic.parentId,
                title = topic.title,
                subtitle = topic.subtitle,
                difficulty = topic.difficulty
            )

            addTopicUseCase.execute(addModel).collect { _result.postValue(it) }
        }
    }

    fun trySetTitle(title: String): InputStatus {
        if (title.isEmpty()) return EMPTY
        topic.value?.title = title
        return SUCCESS
    }

    fun trySetSubTitle(subtitle: String): InputStatus {
        topic.value?.subtitle = subtitle
        return SUCCESS
    }

    private fun isInputConditionsCorrectly(): Boolean {
        topic.value?.let { return trySetTitle(it.title) == SUCCESS }
        return false
    }

    companion object {
        const val TOPIC_ID = "topicId"
        const val PARENT_ID = "parentId"
    }
}