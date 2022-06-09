package ru.sergeyzabelin.mylearning.ui.dictionary

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.sergeyzabelin.mylearning.data.local.db.AppDatabase
import ru.sergeyzabelin.mylearning.data.local.db.TopicRepository
import ru.sergeyzabelin.mylearning.data.model.db.Topic

class DictionaryViewModel(application: Application) : AndroidViewModel(Application()) {

    private val repo = TopicRepository(AppDatabase.getInstance(application).topicDao())

    val topics: LiveData<List<Topic>> = repo.topics
    val isLoading: ObservableBoolean = ObservableBoolean(true )

    fun getAllDictionaryGroup() = viewModelScope.launch {
        isLoading.set(true)
        repo.getAllTopic()
        isLoading.set(false)
    }
}