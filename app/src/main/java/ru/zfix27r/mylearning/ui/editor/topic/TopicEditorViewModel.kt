package ru.zfix27r.mylearning.ui.editor.topic

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import ru.zfix27r.domain.editor.topic.GetTopicEditorModelUseCase
import ru.zfix27r.domain.editor.topic.SaveTopicEditorModel
import ru.zfix27r.domain.editor.topic.SaveTopicEditorModelUseCase
import ru.zfix27r.mylearning.ui.BaseViewModel
import ru.zfix27r.mylearning.ui.editor.EMPTY
import ru.zfix27r.mylearning.ui.editor.OK
import ru.zfix27r.mylearning.ui.getErrorStringRes
import ru.zfix27r.mylearning.ui.getMoreThanZeroOrNull
import ru.zfix27r.mylearning.ui.getNotEmptyOrNull
import javax.inject.Inject

@HiltViewModel
class TopicEditorViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getTopic: GetTopicEditorModelUseCase,
    private val saveTopic: SaveTopicEditorModelUseCase,
) : BaseViewModel() {
    private val _topic = TopicEditorModel(
        savedStateHandle.get<Int?>(TOPIC_ID).getMoreThanZeroOrNull()
    )
    val topicId
        get() = _topic.id
    var topicIconId
        get() = _topic.icon
        set(value) {
            _topic.icon = value
        }
    var topicParentId
        get() = _topic.parentId
        set(value) {
            _topic.parentId = value
        }
    var topicParentTitle
        get() = _topic.parentTitle
        set(value) {
            _topic.parentTitle = value
        }
    var topicParentSubtitle
        get() = _topic.parentSubtitle
        set(value) {
            _topic.parentSubtitle = value
        }
    var topicTitle
        get() = _topic.title
        set(value) {
            _topic.statusTitle = if (value.isNotEmpty()) OK else EMPTY
            _topic.title = value
        }
    var topicSubtitle
        get() = _topic.subtitle
        set(value) {
            _topic.subtitle = value
        }
    val statusTitle
        get() = _topic.statusTitle

    init {
        loading()
    }

    private fun loading() = viewModelScope.launch(Dispatchers.IO) {
        topicId?.let { id ->
            getTopic.execute(id)
                .onStart { start() }
                .catch { error(it.getErrorStringRes()) }
                .collectLatest {
                    _topic.icon = it.iconId ?: 0
                    _topic.title = it.title
                    _topic.statusTitle = OK
                    _topic.subtitle = it.subtitle ?: ""
                    _topic.parentId = it.parentId ?: 0
                    _topic.parentTitle = it.parentTitle ?: ""
                    _topic.parentSubtitle = it.parentSubtitle ?: ""
                    success()
                }
        }
    }

    fun trySave() {
        if (_topic.statusTitle == OK) {
            if (isBlockInput) return
            isBlockInput = true

            viewModelScope.launch(Dispatchers.IO) {
                saveTopic.execute(getSaveTopicEditorModel())
                    .onStart { start() }
                    .catch { error(it.getErrorStringRes()) }
                    .collectLatest { finish() }
            }
        }
    }

    private fun getSaveTopicEditorModel() =
        SaveTopicEditorModel(
            id = topicId.getMoreThanZeroOrNull(),
            parentId = topicParentId.getMoreThanZeroOrNull(),
            iconId = topicIconId.getMoreThanZeroOrNull(),
            title = topicTitle,
            subtitle = topicSubtitle.getNotEmptyOrNull()
        )

    companion object {
        private const val TOPIC_ID = "topic_id"
    }
}