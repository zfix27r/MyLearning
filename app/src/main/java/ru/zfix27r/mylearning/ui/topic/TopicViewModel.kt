package ru.zfix27r.mylearning.ui.topic

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
import ru.zfix27r.mylearning.ui.getErrorStringRes
import ru.zfix27r.mylearning.ui.getMoreThanZeroOrNull
import ru.zfix27r.mylearning.ui.getNotEmptyOrNull
import javax.inject.Inject

@HiltViewModel
class TopicViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getTopic: GetTopicEditorModelUseCase,
    private val saveTopic: SaveTopicEditorModelUseCase,
) : BaseViewModel() {
    private val _topic = TopicModel(
        id = savedStateHandle.get<Int?>(TOPIC_ID).getMoreThanZeroOrNull()
    )
    val topicId
        get() = _topic.id
    var topicIconId
        get() = _topic.iconId
        set(value) {
            _topic.iconId = value
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
            _topic.title = value
        }
    var topicSubtitle
        get() = _topic.subtitle
        set(value) {
            _topic.subtitle = value
        }

    init {
        loading()
    }

    private fun loading() = viewModelScope.launch(Dispatchers.IO) {
        topicId?.let { id ->
            getTopic.execute(id)
                .onStart { start() }
                .catch { error(it.getErrorStringRes()) }
                .collectLatest {
                    _topic.iconId = it.iconId ?: 0
                    _topic.title = it.title
                    _topic.subtitle = it.subtitle ?: ""
                    _topic.parentId = it.parentId ?: 0
                    _topic.parentTitle = it.parentTitle ?: ""
                    _topic.parentSubtitle = it.parentSubtitle ?: ""
                    success()
                }
        }
    }

    fun save(title: String, subtitle: String) {
        _topic.title = title
        _topic.subtitle = subtitle

        viewModelScope.launch(Dispatchers.IO) {
            saveTopic.execute(getSaveTopicEditorModel())
                .onStart { start() }
                .catch { error(it.getErrorStringRes()) }
                .collectLatest { }
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