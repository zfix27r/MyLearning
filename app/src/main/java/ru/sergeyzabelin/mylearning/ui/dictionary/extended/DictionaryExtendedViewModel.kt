package ru.sergeyzabelin.mylearning.ui.dictionary.extended

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.sergeyzabelin.mylearning.data.DictionaryPreferences
import ru.sergeyzabelin.mylearning.data.common.Resource
import ru.sergeyzabelin.mylearning.data.model.db.Dictionary
import ru.sergeyzabelin.mylearning.domain.usecases.GetDictionaryUseCase
import javax.inject.Inject

@HiltViewModel
class DictionaryExtendedViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getDictionaryUseCase: GetDictionaryUseCase,
    private val dictionaryPreferences: DictionaryPreferences
) : ViewModel() {

    val savedTopicId: Long = savedStateHandle.get<Long>("topicId")!!

    val data: LiveData<Resource<Dictionary>> = getDictionaryUseCase.execute(savedTopicId)

    fun getMode(): DictionaryPreferences.MODE = dictionaryPreferences.getModeView()
    fun setMode(mode: DictionaryPreferences.MODE) = dictionaryPreferences.setModeView(mode)
}