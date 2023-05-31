package ru.zfix27r.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.zfix27r.data.local.dao.*
import ru.zfix27r.data.local.db.TopicQuoteCrossRefDbEntity
import ru.zfix27r.data.local.entity.QuestionEntity
import ru.zfix27r.data.local.entity.QuoteEntity
import ru.zfix27r.data.local.entity.SourceEntity
import ru.zfix27r.data.local.entity.TopicEntity

@Database(
    entities = [
        TopicEntity::class,
        QuoteEntity::class,
        SourceEntity::class,
        QuestionEntity::class,
        TopicQuoteCrossRefDbEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dictionaryDao(): DictionaryDao
    abstract fun contentDao(): ContentDao
    abstract fun quoteDao(): QuoteDao
    abstract fun sourceDao(): SourceDao
    abstract fun questionDao(): QuestionDao
}