package ru.sergeyzabelin.mylearning.ui.dictionary.editor

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.sergeyzabelin.mylearning.data.DictionaryPreferences
import ru.sergeyzabelin.mylearning.data.common.Resource
import ru.sergeyzabelin.mylearning.data.model.db.Dictionary
import ru.sergeyzabelin.mylearning.data.model.db.Topic
import ru.sergeyzabelin.mylearning.domain.usecases.DeleteTopicUseCase
import ru.sergeyzabelin.mylearning.domain.usecases.GetDictionaryUseCase
import javax.inject.Inject


@HiltViewModel
class DictionaryEditorViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getDictionaryUseCase: GetDictionaryUseCase,
    private val deleteTopicUseCase: DeleteTopicUseCase,
    private val dictionaryPreferences: DictionaryPreferences
) : ViewModel() {

    val savedTopicId: Long = savedStateHandle.get<Long>("topicId")!!

    val data: LiveData<Resource<Dictionary>> = getDictionaryUseCase.execute(savedTopicId)

    fun topicDelete(topic: Topic) = viewModelScope.launch { deleteTopicUseCase.execute(topic) }

    fun getMode(): DictionaryPreferences.MODE = dictionaryPreferences.getModeView()
    fun setMode(mode: DictionaryPreferences.MODE) = dictionaryPreferences.setModeView(mode)
}