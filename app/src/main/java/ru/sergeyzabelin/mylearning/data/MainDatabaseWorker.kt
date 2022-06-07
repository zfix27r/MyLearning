package ru.sergeyzabelin.mylearning.data

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sergeyzabelin.mylearning.data.entities.Dictionary

class MainDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    private val dao = MainDatabase.getInstance(applicationContext).mainDao()

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            applicationContext.assets.open(JSON_FILEPATH_DIRECTORY).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val type = object : TypeToken<List<Dictionary>>() {}.type
                    val list: List<Dictionary> = Gson().fromJson(jsonReader, type)
                    dao.setAllDictionary(list)
                    Result.success()
                }
            }

        } catch (ex: Exception) {
            Result.failure()
        }
    }

    companion object {
        const val JSON_FILEPATH_DIRECTORY = "dictionary.json"
    }
}