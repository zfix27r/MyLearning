package ru.sergeyzabelin.mylearning.data

import android.content.Context
import android.util.JsonReader
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import ru.sergeyzabelin.mylearning.data.entities.Lesson

class MainDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    private val dao = MainDatabase.getInstance(applicationContext).mainDao()

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            applicationContext.assets.open("lesson_topic.json").use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    Log.e("asdad", Json.decodeFromString<Lesson>(jsonReader.toString()).toString())
/*                    val type = object : TypeToken<List<LessonTopic>>() {}.type
                    val list: List<LessonTopic> = Gson().fromJson(jsonReader, type)
                    dao.setAllStoryTitle(list)*/
                    Result.success()
                }
            }

        } catch (ex: Exception) {
            Result.failure()
        }
    }
}