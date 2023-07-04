package ru.zfix27r.mylearning.ui.editor.topic.parent

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
import ru.zfix27r.mylearning.ui.BaseViewModel
import ru.zfix27r.mylearning.ui.getErrorStringRes
import javax.inject.Inject

@HiltViewModel
class TopicEditorParentViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getTopicsByTopicId: GetTopicEditorParentModelByTopicIdUseCase,
    private val getTopicsByTopicParentId: GetTopicEditorParentModelByTopicParentIdUseCase,
) : BaseViewModel() {
    private val initTopicId = savedStateHandle.get<Int>(TOPIC_ID) ?: 0
    val initTopicParentId = savedStateHandle.get<Int>(TOPIC_PARENT_ID) ?: 0

    private val _navQueue = mutableListOf<Int>()

    private val _topics = MutableLiveData<List<TopicEditorParentAdapterModel>>()
    val topics: LiveData<List<TopicEditorParentAdapterModel>> = _topics

    init {
        println("@@!! initTopicId = $initTopicId,  initTopicParentId = $initTopicParentId")
        _navQueue.add(initTopicParentId)
        loadingByTopicParentId()
    }

    fun navToSelf(topicId: Int) {
        _navQueue.add(topicId)
        loadingByTopicId()
    }

    fun navPopBack(): Boolean {
        if (_navQueue.last() == 0) return false
        else {
            if (_navQueue.size > 1) {
                _navQueue.removeLast()
                loadingByTopicId()
            } else
                loadingByTopicParentId()

        }

        return true
    }

    private fun loadingByTopicParentId() = viewModelScope.launch(Dispatchers.IO) {
        getTopicsByTopicParentId
            .execute(_navQueue.last())
            .onStart { start() }
            .catch { error(it.getErrorStringRes()) }
            .collectLatest {
                val topicParentId = it.firstOrNull()?.parentId ?: 0

                _navQueue.clear()
                _navQueue.add(topicParentId)

                _topics.postValue(it.toTopicEditorParentUIModel())
                success()
            }
    }

    private fun loadingByTopicId() = viewModelScope.launch(Dispatchers.IO) {
        getTopicsByTopicId
            .execute(_navQueue.last())
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
                TopicEditorParentAdapterModel(
                    id = id,
                    parentId = parentId ?: 0,
                    title = title,
                    childCount = childCount ?: 0,
                    subtitle = subtitle ?: "",
                    isEditable = id == initTopicId,
                    isSelect = id == initTopicParentId
                )
            }
        }

    companion object {
        const val TOPIC_ID = "topic_id"
        const val TOPIC_PARENT_ID = "topic_parent_id"
    }
}