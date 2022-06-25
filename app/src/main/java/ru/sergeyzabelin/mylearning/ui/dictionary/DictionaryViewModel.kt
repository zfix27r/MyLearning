package ru.sergeyzabelin.mylearning.ui.dictionary

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.sergeyzabelin.mylearning.data.common.Resource
import ru.sergeyzabelin.mylearning.data.model.db.DictionaryData
import ru.sergeyzabelin.mylearning.di.usecases.GetDictionaryTopicsUseCase
import javax.inject.Inject


@HiltViewModel
class DictionaryViewModel @Inject constructor(
    getDictionaryUseCase: GetDictionaryTopicsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val savedTopicId = savedStateHandle.get<Long>("topicId")!!

    val data: LiveData<Resource<DictionaryData>> =
        getDictionaryUseCase.execute(savedTopicId)

}