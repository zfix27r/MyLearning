package ru.sergeyzabelin.mylearning.ui.dictionary.detail

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.sergeyzabelin.mylearning.data.local.db.dictionarydetail.DictionaryDetailRepository
import ru.sergeyzabelin.mylearning.data.model.db.Dictionary

class DictionaryDetailViewModel(application: Application): AndroidViewModel(Application()) {
    private val repo = DictionaryDetailRepository(application)

    val dictionaryDetail: LiveData<Dictionary> = repo.getDictionary()

    var dictionaryDetailId: Int = 0

    var isLoading: ObservableBoolean = ObservableBoolean(true)

    fun getDictionaryById() = viewModelScope.launch {
        isLoading.set(true)
        repo.getDictionaryDetailById(dictionaryDetailId)
        isLoading.set(false)
    }
}