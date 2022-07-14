package ru.sergeyzabelin.mylearning.ui.dictionary

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.sergeyzabelin.mylearning.data.common.Resource
import ru.sergeyzabelin.mylearning.data.model.db.Dictionary
import ru.sergeyzabelin.mylearning.data.model.db.Topic
import ru.sergeyzabelin.mylearning.domain.usecases.DeleteTopicUseCase
import ru.sergeyzabelin.mylearning.domain.usecases.GetDictionaryUseCase
import javax.inject.Inject


@HiltViewModel
class DictionaryViewModel @Inject constructor(
    getDictionaryUseCase: GetDictionaryUseCase,
    savedStateHandle: SavedStateHandle,
    private val deleteTopicUseCase: DeleteTopicUseCase
) : ViewModel() {

    val savedTopicId: Long = savedStateHandle.get<Long>("topicId")!!

    val data: LiveData<Resource<Dictionary>> =
        getDictionaryUseCase.execute(savedTopicId)

    fun topicDelete(topic: Topic) = viewModelScope.launch { deleteTopicUseCase.execute(topic) }
}