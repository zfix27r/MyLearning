package ru.sergeyzabelin.mylearning.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import ru.sergeyzabelin.mylearning.data.model.db.Quote
import ru.sergeyzabelin.mylearning.data.model.db.Source
import ru.sergeyzabelin.mylearning.data.model.db.Topic
import ru.sergeyzabelin.mylearning.data.model.db.TopicQuoteCrossRef

@Dao
interface AppDatabaseWorkerDao {

    @Insert
    suspend fun setAllTopic(list: List<Topic>)

    @Insert
    suspend fun setAllArticle(list: List<Quote>)

    @Insert
    suspend fun setAllTopicArticleCrossRef(list: List<TopicQuoteCrossRef>)

    @Insert
    suspend fun setAllSource(list: List<Source>)
}