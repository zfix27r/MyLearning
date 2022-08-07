package ru.sergeyzabelin.mylearning.ui.dictionary

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.zfix27r.data.common.Resource
import ru.zfix27r.data.model.db.Dictionary
import ru.zfix27r.data.model.db.Topic
import ru.sergeyzabelin.mylearning.domain.usecases.DeleteTopicUseCase
import ru.sergeyzabelin.mylearning.domain.usecases.GetDictionaryUseCase
import ru.sergeyzabelin.mylearning.utils.AppConstants.Companion.TOPIC_ID
import javax.inject.Inject


@HiltViewModel
class DictionaryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getDictionaryUseCase: GetDictionaryUseCase,
    private val deleteTopicUseCase: DeleteTopicUseCase
) : ViewModel() {

    val topicId: Long = savedStateHandle.get<Long>(TOPIC_ID) ?: 0

    val data: LiveData<Resource<Dictionary>> =
        getDictionaryUseCase.execute(topicId)

    fun topicDelete(topic: Topic) = viewModelScope.launch { deleteTopicUseCase.execute(topic) }
}