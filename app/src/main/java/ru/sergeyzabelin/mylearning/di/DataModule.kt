package ru.sergeyzabelin.mylearning.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.zfix27r.data.local.db.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        //val request = OneTimeWorkRequestBuilder<AppDatabaseWorker>().build()
        //WorkManager.getInstance(context).enqueue(request)

        return Room.databaseBuilder(
            context, AppDatabase::class.java,
            "my_learning"
        ).createFromAsset("my_learning.db").build()

        /*.addCallback(object : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            MainScope().launch(Dispatchers.Main) {

            }
        }
    }).fallbackToDestructiveMigration()*/
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
