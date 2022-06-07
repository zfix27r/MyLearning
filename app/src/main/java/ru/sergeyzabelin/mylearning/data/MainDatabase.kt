package ru.sergeyzabelin.mylearning.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import ru.sergeyzabelin.mylearning.data.entities.Dictionary

@Database(
    entities = [Dictionary::class],
    version = 1,
    exportSchema = false
)
abstract class MainDatabase : RoomDatabase() {
    abstract fun mainDao(): MainDao

    companion object {
        @Volatile
        private var instance: MainDatabase? = null

        fun getInstance(context: Context): MainDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): MainDatabase {
            return Room.databaseBuilder(
                context, MainDatabase::class.java,
                "my_lesson"
            ).addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    val request = OneTimeWorkRequestBuilder<MainDatabaseWorker>().build()
                    WorkManager.getInstance(context).enqueue(request)
                }
            }).fallbackToDestructiveMigration().build()
        }
    }
}