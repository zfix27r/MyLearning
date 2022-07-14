package ru.sergeyzabelin.mylearning.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.sergeyzabelin.mylearning.data.model.db.Quote
import ru.sergeyzabelin.mylearning.data.model.db.Source
import ru.sergeyzabelin.mylearning.data.model.db.Topic
import ru.sergeyzabelin.mylearning.data.model.db.TopicQuoteCrossRef

@Database(
    entities = [
        Topic::class,
        Quote::class,
        TopicQuoteCrossRef::class,
        Source::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun databaseWorkerDao(): AppDatabaseWorkerDao

    abstract fun mainDao(): MainDao

    abstract fun topicDao(): DictionaryDao
}