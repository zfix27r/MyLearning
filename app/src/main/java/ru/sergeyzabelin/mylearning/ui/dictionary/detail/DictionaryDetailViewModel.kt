package ru.sergeyzabelin.mylearning.ui.dictionary.detail

import android.app.Application
import androidx.databinding.ObservableInt
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.sergeyzabelin.mylearning.data.DetailDictionaryRepository
import ru.sergeyzabelin.mylearning.data.local.db.AppDatabase
import ru.sergeyzabelin.mylearning.data.model.db.Article
import ru.sergeyzabelin.mylearning.data.model.db.TopicWithArticles
import ru.sergeyzabelin.mylearning.utils.ApiBuilder

class DictionaryDetailViewModel(application: Application) : AndroidViewModel(Application()) {
    private val repo = DetailDictionaryRepository(
        ApiBuilder,
        AppDatabase.getInstance(application).topicDao()
    )

    var topicId: Long = 0

    var topicWithArticles: LiveData<TopicWithArticles>? = null

    //  = 0 - false, > 0 - true
    private var loadingCounter: Int = 0
        set(value) {
            field = value
            isLoadingCounter.set(field)
        }
    val isLoadingCounter: ObservableInt = ObservableInt(0)


    fun getTopicWithArticlesById() {
        loadingCounter++
        topicWithArticles = repo.getTopicWithArticlesById(topicId)
        loadingCounter--
    }

    fun refreshDescription(article: Article) = viewModelScope.launch {
        //val wikiDetail = repo.getDescriptionById(article.)
        //repo.setArticle(article.copy(description = wikiDetail.query.pages[0].extract))
    }
}