package ru.zfix27r.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import ru.sergeyzabelin.mylearning.data.model.db.*
import ru.zfix27r.data.model.db.Question
import ru.zfix27r.data.model.db.Quote
import ru.zfix27r.data.model.db.Topic
import ru.zfix27r.data.model.db.TopicQuoteCrossRef

@Dao
interface WorkerDao {

    @Insert
    suspend fun setAllTopic(list: List<Topic>)

    @Insert
    suspend fun setAllQuote(list: List<Quote>)

    @Insert
    suspend fun setAllTopicQuoteCrossRef(list: List<TopicQuoteCrossRef>)

    @Insert
    suspend fun setAllSource(list: List<SourceDao>)

    @Insert
    suspend fun setAllQuestion(list: List<Question>)
}