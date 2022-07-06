package ru.sergeyzabelin.mylearning.domain.repository

import androidx.lifecycle.LiveData
import ru.sergeyzabelin.mylearning.data.common.Resource
import ru.sergeyzabelin.mylearning.data.model.db.Article
import ru.sergeyzabelin.mylearning.data.model.db.Dictionary
import ru.sergeyzabelin.mylearning.data.model.db.Topic
import ru.sergeyzabelin.mylearning.domain.model.SaveTopicModel

interface DictionaryRepository {

    fun getDictionaryBy(id: Long): LiveData<Resource<Dictionary>>

    fun getDictionaryTopicBy(id: Long): LiveData<Resource<Topic>>

    suspend fun saveDictionaryTopic(saveTopicModel: SaveTopicModel)

    suspend fun saveDictionaryArticle(article: Article)
}