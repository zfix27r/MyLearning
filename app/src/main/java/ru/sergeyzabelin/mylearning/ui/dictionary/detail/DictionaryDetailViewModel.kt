package ru.sergeyzabelin.mylearning.ui.dictionary.detail

import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.sergeyzabelin.mylearning.data.DetailDictionaryRepository
import ru.sergeyzabelin.mylearning.data.model.db.Article
import ru.sergeyzabelin.mylearning.data.model.db.TopicWithArticles
import javax.inject.Inject

class DictionaryDetailViewModel @Inject constructor(repository: DetailDictionaryRepository) : ViewModel() {
    var topicId: Long = 0

    var topicWithArticles: LiveData<TopicWithArticles> = repository.getTopicWithArticlesById(topicId)

    //  = 0 - false, > 0 - true
    private var loadingCounter: Int = 0
        set(value) {
            field = value
            isLoadingCounter.set(field)
        }
    val isLoadingCounter: ObservableInt = ObservableInt(0)

    fun refreshDescription(article: Article) = viewModelScope.launch {
        //val wikiDetail = repo.getDescriptionById(article.)
        //repo.setArticle(article.copy(description = wikiDetail.query.pages[0].extract))
    }
}