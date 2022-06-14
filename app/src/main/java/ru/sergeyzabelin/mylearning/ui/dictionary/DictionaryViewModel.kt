package ru.sergeyzabelin.mylearning.ui.dictionary

import android.app.Application
import androidx.databinding.ObservableInt
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.sergeyzabelin.mylearning.data.DictionaryRepository
import ru.sergeyzabelin.mylearning.data.local.db.AppDatabase
import ru.sergeyzabelin.mylearning.data.model.db.Topic

class DictionaryViewModel(application: Application) : AndroidViewModel(Application()) {

    private val repo = DictionaryRepository(AppDatabase.getInstance(application).topicDao())

    var topicId: Long = 0

    var topics: LiveData<List<Topic>>? = null

    //  = 0 - false, > 0 - true
    private var loadingCounter: Int = 0
        set(value) {
            field = value
            isLoadingCounter.set(field)
        }
    val isLoadingCounter: ObservableInt = ObservableInt(0)

    fun getNextDictionaryGroup() = viewModelScope.launch {
        loadingCounter++
        topics = repo.getTopicByParentId(topicId)
        loadingCounter--
    }
}