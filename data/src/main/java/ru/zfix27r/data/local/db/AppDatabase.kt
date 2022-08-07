package ru.zfix27r.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.zfix27r.data.model.db.Question
import ru.zfix27r.data.model.db.Quote
import ru.zfix27r.data.model.db.Topic
import ru.zfix27r.data.model.db.TopicQuoteCrossRef

@Database(
    entities = [
        Topic::class,
        Quote::class,
        SourceDao::class,
        Question::class,
        TopicQuoteCrossRef::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun workerDao(): WorkerDao
    abstract fun dictionaryDao(): DictionaryDao
    abstract fun contentDao(): ContentDao
    abstract fun topicDao(): TopicDao
    abstract fun quoteDao(): QuoteDao
    abstract fun sourceDao(): SourceDao
    abstract fun questionDao(): QuestionDao
}