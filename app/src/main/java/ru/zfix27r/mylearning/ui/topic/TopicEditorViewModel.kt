package ru.zfix27r.mylearning.ui.topic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.zfix27r.domain.topicEditor.GetTopic4TopicEditorUseCase
import ru.zfix27r.domain.topicEditor.SaveTopic4TopicEditorModel
import ru.zfix27r.domain.topicEditor.SaveTopic4TopicEditorUseCase
import ru.zfix27r.mylearning.ui.BaseViewModel
import ru.zfix27r.mylearning.ui.SingleLiveData
import ru.zfix27r.mylearning.ui.UIResult
import ru.zfix27r.mylearning.ui.getErrorStringRes
import javax.inject.Inject

@HiltViewModel
class TopicEditorViewModel @Inject constructor(
    private val getTopicUseCase: GetTopic4TopicEditorUseCase,
    private val saveTopicUseCase: SaveTopic4TopicEditorUseCase,
) : BaseViewModel() {
    private val _isDone = SingleLiveData<Boolean>()
    val isDone: LiveData<Boolean> = _isDone

    private val _isUpdate = MutableLiveData(0)
    val isUpdate: LiveData<Int> = _isUpdate

    private val _topic = TopicEditorSaveModel()
    val topicId
        get() = _topic.id ?: 0
    val topicParentId
        get() = _topic.parentId ?: 0
    val topicParentTitle
        get() = _topic.parentTitle
    val topicParentSubtitle
        get() = _topic.parentSubtitle
    val topicTitle
        get() = _topic.title
    val topicSubtitle
        get() = _topic.subtitle

    override fun loading(id: Int): Job = viewModelScope.launch(Dispatchers.IO) {
        if (id > 0) {
            getTopicUseCase.execute(id).collectLatest {
                _topic.id = it.id
                _topic.parentId = it.parentId
                _topic.parentTitle = it.parentTitle
                _topic.parentSubtitle = it.parentSubtitle
                _topic.title = it.title
                _topic.isTitleCorrectly = true
                _topic.subtitle = it.subtitle
                _topic.difficulty = it.difficulty
                _isUpdate.postValue(_isUpdate.value!! + 1)
            }
        }
    }

    fun String.setTitle(): UIResult {
        val result = checkTitle()
        if (result == UIResult.OK) {
            _topic.title = this
            _topic.isTitleCorrectly = true
        } else _topic.isTitleCorrectly = false
        return result
    }

    fun String.setSubtitle(): UIResult {
        _topic.subtitle = this
        return UIResult.OK
    }

    fun done() = viewModelScope.launch(Dispatchers.IO) {
        println("!!!!!!!!!!!!!!! 2")

        _topic.run {
            if (isTitleCorrectly || title.checkTitle() == UIResult.OK)
                saveTopicUseCase
                    .execute(toSaveTopicModel())
                    .catch { onError(it.getErrorStringRes()) }
                    .collectLatest {
                        println("!!!!!!!!!!!!!!! 3")

                        _isDone.postValue(true) }
        }
    }

    fun setTopicParent(id: Int?, title: String?, subtitle: String?) {
        _topic.parentId = id
        _topic.parentTitle = title
        _topic.parentSubtitle = subtitle
        _isUpdate.postValue(_isUpdate.value!! + 1)
    }

    private fun String.checkTitle() =
        if (isEmpty()) UIResult.FIELD_EMPTY
        else UIResult.OK


    private fun TopicEditorSaveModel.toSaveTopicModel() =
        SaveTopic4TopicEditorModel(id, parentId, title, subtitle, difficulty)
}