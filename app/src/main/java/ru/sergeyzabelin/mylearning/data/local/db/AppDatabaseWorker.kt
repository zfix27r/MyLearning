package ru.sergeyzabelin.mylearning.data.local.db

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sergeyzabelin.mylearning.data.model.db.Article
import ru.sergeyzabelin.mylearning.data.model.db.Topic
import ru.sergeyzabelin.mylearning.data.model.db.TopicArticleCrossRef
import ru.sergeyzabelin.mylearning.utils.AppConstants

@HiltWorker
class AppDatabaseWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val dao: AppDatabaseWorkerDao
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.Main) {

        try {
            applicationContext.assets.open(AppConstants.ASSET_TOPIC_FILEPATH)
                .use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val type = object : TypeToken<List<Topic>>() {}.type
                        val list: List<Topic> = Gson().fromJson(jsonReader, type)
                        dao.setAllTopic(list)
                        Result.success()
                    }
                }

            applicationContext.assets.open(AppConstants.ASSET_ARTICLE_FILEPATH)
                .use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val type = object : TypeToken<List<Article>>() {}.type
                        val list: List<Article> = Gson().fromJson(jsonReader, type)
                        dao.setAllArticle(list)
                        Result.success()
                    }
                }

            applicationContext.assets.open(AppConstants.ASSET_TOPIC_ARTICLE_CROSS_REF_FILEPATH)
                .use { inputStream ->
                    Log.e("test", "1")
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        Log.e("test", "2")
                        val type = object : TypeToken<List<TopicArticleCrossRef>>() {}.type
                        Log.e("test", "3")
                        val list: List<TopicArticleCrossRef> = Gson().fromJson(jsonReader, type)
                        Log.e("test", list.toString())
                        try {
                            dao.setAllTopicArticleCrossRef(list)
                        } catch (e: Exception) {
                            Log.e("test", e.toString())
                        }
                        Result.success()
                    }
                }

        } catch (ex: Exception) {
            Result.failure()
        }
    }
}