package ru.sergeyzabelin.mylearning.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.zfix27r.data.local.db.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideDb(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app, AppDatabase::class.java,
            "my_learning"
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                val request = OneTimeWorkRequestBuilder<AppDatabaseWorker>().build()
                WorkManager.getInstance(app).enqueue(request)
            }
        }).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideWorkerDao(db: AppDatabase): WorkerDao {
        return db.workerDao()
    }

    @Singleton
    @Provides
    fun provideDictionaryDao(db: AppDatabase): DictionaryDao {
        return db.dictionaryDao()
    }

    @Singleton
    @Provides
    fun provideContentDao(db: AppDatabase): ContentDao {
        return db.contentDao()
    }

    @Singleton
    fun provideQuoteDao(db: AppDatabase): QuoteDao {
        return db.quoteDao()
    }
}