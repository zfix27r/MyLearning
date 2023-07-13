package ru.zfix27r.mylearning.ui.topic.parent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import ru.zfix27r.domain.editor.topic.parent.GetTopicEditorParentModel
import ru.zfix27r.domain.editor.topic.parent.GetTopicEditorParentModelByTopicParentIdUseCase
import ru.zfix27r.domain.editor.topic.parent.GetTopicEditorParentModelByTopicIdUseCase
import ru.zfix27r.mylearning.ui.base.BaseViewModel
import ru.zfix27r.mylearning.ui.getErrorStringRes
import javax.inject.Inject

@HiltViewModel
class TopicParentViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getTopicsByTopicId: GetTopicEditorParentModelByTopicIdUseCase,
    private val getTopicsByTopicParentId: GetTopicEditorParentModelByTopicParentIdUseCase,
) : BaseViewModel() {
    private val _ui = MutableLiveData(
        TopicParentUIModel(
            topicId = savedStateHandle.get<Int>(TOPIC_ID) ?: 0,
            topicParentId = savedStateHandle.get<Int>(TOPIC_PARENT_ID) ?: 0,
        )
    )
    private val ui = _ui.value!!
    val uiObservable: LiveData<TopicParentUIModel> = _ui
    var isEditing
        get() = ui.isEditing
        set(value) {
            ui.isEditing = value
            _ui.postValue(ui)

            topics.value
                ?.map { it.apply { isShowEditUI = value } }
                ?.let { _topics.postValue(it) }
        }
    val topicParentId
        get() = ui.topicParentId

    private val _topics = MutableLiveData<List<TopicParentAdapterModel>>()
    val topics: LiveData<List<TopicParentAdapterModel>> = _topics

    init {
        ui.navQueue.add(ui.topicParentId)
        loadingByTopicParentId()
    }

    fun navToSelf(topicId: Int) {
        ui.navQueue.add(topicId)
        loadingByTopicId()
    }

    fun navPopBack(): Boolean {
        if (ui.navQueue.last() == 0) return false
        else {
            if (ui.navQueue.size > 1) {
                ui.navQueue.removeLast()
                loadingByTopicId()
            } else
                loadingByTopicParentId()

        }

        return true
    }

    private fun loadingByTopicParentId() = viewModelScope.launch(Dispatchers.IO) {
        getTopicsByTopicParentId
            .execute(ui.navQueue.last())
            .onStart { start() }
            .catch { error(it.getErrorStringRes()) }
            .collectLatest {
                val topicParentId = it.firstOrNull()?.parentId ?: 0

                ui.navQueue.clear()
                ui.navQueue.add(topicParentId)

                _topics.postValue(it.toTopicEditorParentUIModel())
                success()
            }
    }

    private fun loadingByTopicId() = viewModelScope.launch(Dispatchers.IO) {
        getTopicsByTopicId
            .execute(ui.navQueue.last())
            .onStart { start() }
            .catch { error(it.getErrorStringRes()) }
            .collectLatest {
                _topics.postValue(it.toTopicEditorParentUIModel())
                success()
            }
    }

    private fun List<GetTopicEditorParentModel>.toTopicEditorParentUIModel() =
        map {
            it.run {
                TopicParentAdapterModel(
                    id = id,
                    parentId = parentId ?: 0,
                    title = title,
                    childCount = childCount ?: 0,
                    subtitle = subtitle ?: "",
                    isEditable = id == ui.topicId,
                    isSelect = id == ui.topicParentId,
                    isShowEditUI = isEditing
                )
            }
        }

    companion object {
        const val TOPIC_ID = "topic_id"
        const val TOPIC_PARENT_ID = "topic_parent_id"
    }
}