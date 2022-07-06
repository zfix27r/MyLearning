package ru.sergeyzabelin.mylearning.ui.dictionary

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.sergeyzabelin.mylearning.domain.usecases.GetDictionarySaveTopicModelUseCase
import ru.sergeyzabelin.mylearning.domain.usecases.SaveDictionaryTopicUseCase
import javax.inject.Inject


@HiltViewModel
class DictionaryTopicAddViewModel @Inject constructor(
    getDictionaryTopicUseCase: GetDictionarySaveTopicModelUseCase,
    private val saveDictionaryTopicUseCase: SaveDictionaryTopicUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val topicId: Long = savedStateHandle.get<Long>("topicId")!!
    private val isModeAdd: Boolean = savedStateHandle.get<Boolean>("isModeAdd")!!

    //val saveTopicModel: LiveData<Resource<SaveTopicModel>> = getDictionaryTopicUseCase.execute(topicId)


/*    fun addSaveTopicModel() = viewModelScope.launch {
        saveDictionaryTopicUseCase.execute(saveTopicModel)
    }*/
}