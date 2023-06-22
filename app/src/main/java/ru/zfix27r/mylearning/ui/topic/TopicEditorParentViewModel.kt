package ru.zfix27r.mylearning.ui.topic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.zfix27r.domain.topicEditor.GetTopics4TopicEditorParentModel
import ru.zfix27r.domain.topicEditor.GetTopicsByTopicId4TopicEditorUseCase
import ru.zfix27r.domain.topicEditor.GetTopicsByTopicParentId4TopicEditorUseCase
import ru.zfix27r.mylearning.R
import ru.zfix27r.mylearning.ui.BaseViewModel
import ru.zfix27r.mylearning.ui.getErrorStringRes
import javax.inject.Inject

@HiltViewModel
class TopicEditorParentViewModel @Inject constructor(
    private val getTopicsByTopicId: GetTopicsByTopicId4TopicEditorUseCase,
    private val getTopicsByTopicParentId: GetTopicsByTopicParentId4TopicEditorUseCase,
) : BaseViewModel() {
    private var _topicId = 0
    val topicId
        get() = _topicId

    private var _topicIdSelf = 0
    val topicIdSelf
        get() = _topicIdSelf

    private var _topicIdChecked = 0
    val topicIdChecked
        get() = _topicIdChecked

    private val _topics = MutableLiveData<List<TopicEditorParentUIModel>>()
    val topics: LiveData<List<TopicEditorParentUIModel>> = _topics

    override fun loading(id: Int) = TODO()

    fun loading(topicId: Int, topicIdSelf: Int, topicIdChecked: Int, isTopicParentId: Boolean) =
        viewModelScope.launch(Dispatchers.IO) {
            _topicId = topicId
            _topicIdSelf = topicIdSelf
            _topicIdChecked = topicIdChecked

            if (isTopicParentId) loadingByTopicParentId() else loadingByTopicId()
        }

    fun returnParentToTopicEditor(navController: NavController, topic: TopicEditorParentUIModel) {
        val savedState = navController.getBackStackEntry(R.id.topic_editor).savedStateHandle

        if (topic.id == _topicIdChecked)
            savedState.putBackStackSavedState(0, null, null)
        else
            savedState.putBackStackSavedState(topic.id, topic.title, topic.subtitle)

        navController.popBackStack(R.id.topic_editor, false)
    }

    private fun SavedStateHandle.putBackStackSavedState(
        id: Int,
        title: String?,
        subtitle: String?
    ) {
        this[TopicEditorFragment.NEW_TOPIC_PARENT] = true
        this[TopicEditorFragment.NEW_TOPIC_PARENT_ID] = id
        this[TopicEditorFragment.NEW_TOPIC_PARENT_TITLE] = title
        this[TopicEditorFragment.NEW_TOPIC_PARENT_SUBTITLE] = subtitle
    }

    private suspend fun loadingByTopicParentId() {
        getTopicsByTopicParentId
            .execute(_topicId, _topicIdSelf)
            .catch { onError(it.getErrorStringRes()) }
            .collectLatest {
                _topics.postValue(it.toTopicEditorParentUIModel())
            }
    }

    private suspend fun loadingByTopicId() {
        getTopicsByTopicId
            .execute(_topicId, _topicIdSelf)
            .catch { onError(it.getErrorStringRes()) }
            .collectLatest {
                _topics.postValue(it.toTopicEditorParentUIModel())
            }
    }

    private fun List<GetTopics4TopicEditorParentModel>.toTopicEditorParentUIModel() =
        map {
            it.run {
                TopicEditorParentUIModel(id, parentId, title, subtitle, id == _topicIdChecked)
            }
        }
}