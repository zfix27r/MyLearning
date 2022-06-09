package ru.sergeyzabelin.mylearning.ui.dictionary.detail

import android.app.Application
import androidx.databinding.ObservableInt
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.sergeyzabelin.mylearning.data.local.db.AppDatabase
import ru.sergeyzabelin.mylearning.data.local.db.TopicRepository
import ru.sergeyzabelin.mylearning.data.model.db.TopicWithArticles

class DictionaryDetailViewModel(application: Application) : AndroidViewModel(Application()) {
    private val repo = TopicRepository(AppDatabase.getInstance(application).topicDao())

    val topicWithArticles: LiveData<TopicWithArticles> = repo.topicWithArticles

    var topicId: Int = 0

    var isLoadingCounter: ObservableInt = ObservableInt(0)

    fun getTopicWithArticlesById() = viewModelScope.launch {
        upIsLoadingCounter()
        repo.getTopicWithArticlesById(topicId)
        downIsLoadingCounter()
    }

    private fun upIsLoadingCounter() {
        isLoadingCounter.set(isLoadingCounter.get() + 1)
    }

    private fun downIsLoadingCounter() {
        isLoadingCounter.set(isLoadingCounter.get() - 1)
    }

}