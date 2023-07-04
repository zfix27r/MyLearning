package ru.zfix27r.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.zfix27r.data.local.dao.MainDao
import ru.zfix27r.data.local.dao.QuestionDao
import ru.zfix27r.data.local.dao.QuoteDao
import ru.zfix27r.data.local.dao.SourceDao
import ru.zfix27r.data.local.dao.TopicDao
import ru.zfix27r.data.local.entity.QuestionEntity
import ru.zfix27r.data.local.entity.QuoteEntity
import ru.zfix27r.data.local.entity.SourceEntity
import ru.zfix27r.data.local.entity.TopicEntity

@Database(
    entities = [
        TopicEntity::class,
        QuoteEntity::class,
        SourceEntity::class,
        QuestionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mainDao(): MainDao
    abstract fun topicDao(): TopicDao
    abstract fun quoteDao(): QuoteDao
    abstract fun sourceDao(): SourceDao
    abstract fun questionDao(): QuestionDao
}