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

    val topic: LiveData<Resource<Topic>> = getDictionaryTopicUseCase.execute(topicId)
    val saveTopicModel: SaveTopicModel = SaveTopicModel(topicParentId = topicId)

    fun addSaveTopicModel() = viewModelScope.launch {
        //saveDictionaryTopicUseCase.execute(saveTopicModel)
    }

    fun updateSaveTopicModelFromTopic() {
        saveTopicModel.title = topic.value?.data?.title ?: ""
        saveTopicModel.label = topic.value?.data?.label ?: ""
    }



}