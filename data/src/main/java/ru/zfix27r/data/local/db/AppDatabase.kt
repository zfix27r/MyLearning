package ru.zfix27r.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.zfix27r.data.model.db.*

@Database(
    entities = [
        TopicDbEntity::class,
        QuoteDbEntity::class,
        SourceDbEntity::class,
        QuestionDbEntity::class,
        TopicQuoteCrossRefDbEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun workerDao(): WorkerDao
    abstract fun dictionaryDao(): DictionaryDao
    abstract fun contentDao(): ContentDao
    abstract fun quoteDao(): QuoteDao
    abstract fun sourceDao(): SourceDao
    abstract fun questionDao(): QuestionDao
}