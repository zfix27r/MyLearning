package ru.sergeyzabelin.mylearning.data.local.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.sergeyzabelin.mylearning.data.model.db.Topic
import ru.sergeyzabelin.mylearning.data.model.db.TopicWithArticles

class TopicRepository(private val dao: TopicDao) {
    private var _topics: MutableLiveData<List<Topic>> = MutableLiveData()
    val topics: LiveData<List<Topic>> = _topics

    private var _topicWithArticles: MutableLiveData<TopicWithArticles> = MutableLiveData()
    val topicWithArticles: LiveData<TopicWithArticles> = _topicWithArticles

    suspend fun getAllTopic() {
        _topics.postValue(dao.getAllTopic())
    }

    suspend fun getTopicWithArticlesById(id: Int) {
        _topicWithArticles.postValue(dao.getTopicWithArticlesById(id))
    }
}