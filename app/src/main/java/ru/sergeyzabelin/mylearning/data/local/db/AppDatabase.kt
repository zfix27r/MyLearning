package ru.sergeyzabelin.mylearning.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.sergeyzabelin.mylearning.data.model.db.Article
import ru.sergeyzabelin.mylearning.data.model.db.Book
import ru.sergeyzabelin.mylearning.data.model.db.Topic
import ru.sergeyzabelin.mylearning.data.model.db.TopicArticleCrossRef

@Database(
    entities = [
        Topic::class,
        Article::class,
        TopicArticleCrossRef::class,
        Book::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun databaseWorkerDao(): AppDatabaseWorkerDao

    abstract fun mainDao(): MainDao

    abstract fun topicDao(): TopicDao
}