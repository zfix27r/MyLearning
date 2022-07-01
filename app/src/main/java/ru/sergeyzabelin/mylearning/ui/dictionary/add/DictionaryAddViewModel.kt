package ru.sergeyzabelin.mylearning.ui.dictionary.add

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.sergeyzabelin.mylearning.domain.model.SaveTopicModel
import ru.sergeyzabelin.mylearning.domain.usecases.SaveDictionaryTopicUseCase
import javax.inject.Inject

@HiltViewModel
class DictionaryAddViewModel @Inject constructor(
    private val saveDictionaryTopicUseCase: SaveDictionaryTopicUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val saveTopicModel: SaveTopicModel =
        SaveTopicModel(topicParentId = savedStateHandle.get<Long>("selectedTopicId")!!)

    fun addSaveTopicModel() = viewModelScope.launch {
        saveDictionaryTopicUseCase.execute(saveTopicModel)
    }
}