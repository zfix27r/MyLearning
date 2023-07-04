package ru.zfix27r.mylearning.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.zfix27r.data.local.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    private const val DB_NAME = "my_learning"
    private const val DB_FILE_PATH = "$DB_NAME.db"

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
            .createFromAsset(DB_FILE_PATH).build()

    @Singleton
    @Provides
    fun provideMainDao(db: AppDatabase) = db.mainDao()

    @Singleton
    @Provides
    fun provideTopicDao(db: AppDatabase) = db.topicDao()

    @Singleton
    @Provides
    fun provideQuoteDao(db: AppDatabase) = db.quoteDao()

    @Singleton
    @Provides
    fun provideSourceDao(db: AppDatabase) = db.sourceDao()
}
