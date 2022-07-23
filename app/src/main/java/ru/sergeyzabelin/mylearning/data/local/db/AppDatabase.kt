package ru.sergeyzabelin.mylearning.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.sergeyzabelin.mylearning.data.model.db.*

@Database(
    entities = [
        Topic::class,
        Quote::class,
        Source::class,
        Question::class,
        TopicQuoteCrossRef::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun databaseWorkerDao(): AppDatabaseWorkerDao

    abstract fun mainDao(): MainDao

    abstract fun topicDao(): DictionaryDao
}