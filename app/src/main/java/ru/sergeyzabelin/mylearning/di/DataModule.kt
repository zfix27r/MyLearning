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
import ru.sergeyzabelin.mylearning.data.local.db.AppDatabase
import ru.sergeyzabelin.mylearning.data.local.db.AppDatabaseWorker
import ru.sergeyzabelin.mylearning.data.local.db.MainDao
import ru.sergeyzabelin.mylearning.data.local.db.TopicDao
import ru.sergeyzabelin.mylearning.utils.AppConstants
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideDb(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app, AppDatabase::class.java,
            AppConstants.DATABASE_NAME
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
    fun provideMainDao(db: AppDatabase): MainDao {
        return db.mainDao()
    }

    @Singleton
    @Provides
    fun provideTopicDao(db: AppDatabase): TopicDao {
        return db.topicDao()
    }

}