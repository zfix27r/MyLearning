package ru.sergeyzabelin.mylearning.data.local.db

import android.content.Context
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
import ru.sergeyzabelin.mylearning.data.model.db.Quote
import ru.sergeyzabelin.mylearning.data.model.db.Source
import ru.sergeyzabelin.mylearning.data.model.db.Topic
import ru.sergeyzabelin.mylearning.data.model.db.TopicQuoteCrossRef
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

            applicationContext.assets.open(AppConstants.ASSET_QUOTE_FILEPATH)
                .use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val type = object : TypeToken<List<Quote>>() {}.type
                        val list: List<Quote> = Gson().fromJson(jsonReader, type)
                        dao.setAllArticle(list)
                        Result.success()
                    }
                }

            applicationContext.assets.open(AppConstants.ASSET_TOPIC_QUOTE_CROSS_REF_FILEPATH)
                .use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val type = object : TypeToken<List<TopicQuoteCrossRef>>() {}.type
                        val list: List<TopicQuoteCrossRef> = Gson().fromJson(jsonReader, type)
                        dao.setAllTopicArticleCrossRef(list)
                        Result.success()
                    }
                }

            applicationContext.assets.open(AppConstants.ASSET_SOURCE_FILEPATH)
                .use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val type = object : TypeToken<List<Source>>() {}.type
                        val list: List<Source> = Gson().fromJson(jsonReader, type)
                        dao.setAllSource(list)
                        Result.success()
                    }
                }

        } catch (ex: Exception) {
            Result.failure()
        }
    }
}