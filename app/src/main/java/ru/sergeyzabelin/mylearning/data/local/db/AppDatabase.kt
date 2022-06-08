package ru.sergeyzabelin.mylearning.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import ru.sergeyzabelin.mylearning.data.local.db.dictionary.DictionaryDao
import ru.sergeyzabelin.mylearning.data.local.db.dictionarydetail.DictionaryDetailDao
import ru.sergeyzabelin.mylearning.data.local.db.main.MainDao
import ru.sergeyzabelin.mylearning.data.model.db.Dictionary
import ru.sergeyzabelin.mylearning.utils.AppConstants

@Database(
    entities = [Dictionary::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun databaseWorkerDao(): AppDatabaseWorkerDao
    abstract fun mainDao(): MainDao
    abstract fun dictionaryDao(): DictionaryDao
    abstract fun dictionaryDetailDao(): DictionaryDetailDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context, AppDatabase::class.java,
                AppConstants.DATABASE_NAME
            ).addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    val request = OneTimeWorkRequestBuilder<AppDatabaseWorker>().build()
                    WorkManager.getInstance(context).enqueue(request)
                }
            }).fallbackToDestructiveMigration().build()
        }
    }
}