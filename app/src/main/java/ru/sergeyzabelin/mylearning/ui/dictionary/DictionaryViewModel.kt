package ru.sergeyzabelin.mylearning.ui.dictionary

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.sergeyzabelin.mylearning.data.local.db.dictionary.DictionaryRepository
import ru.sergeyzabelin.mylearning.data.model.db.Dictionary

class DictionaryViewModel(application: Application) : AndroidViewModel(Application()) {

    private val repo = DictionaryRepository(application)

    val dictionaryList: LiveData<List<Dictionary>> = repo.getDictionaryGroup()
    val isLoading: ObservableBoolean = ObservableBoolean(true )

    fun getAllDictionaryGroup() = viewModelScope.launch {
        isLoading.set(true)
        repo.getAllDictionary()
        isLoading.set(false)
    }
}