package ru.sergeyzabelin.mylearning.ui.dictionary.detail

import androidx.lifecycle.LiveData
import ru.sergeyzabelin.mylearning.data.model.db.TopicWithArticles

class TopicWithArticlesLiveData(private val topicWithArticles: TopicWithArticles):
    LiveData<TopicWithArticles>() {


}