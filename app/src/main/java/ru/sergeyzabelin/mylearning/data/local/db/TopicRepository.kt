package ru.sergeyzabelin.mylearning.data.local.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.sergeyzabelin.mylearning.data.model.db.Topic

class TopicRepository(private val dao: TopicDao) {
    private var _topics: MutableLiveData<List<Topic>> = MutableLiveData()
    val topics: LiveData<List<Topic>> = _topics

    suspend fun getAllTopic() {
        _topics.postValue(dao.getAllTopic())
    }
}