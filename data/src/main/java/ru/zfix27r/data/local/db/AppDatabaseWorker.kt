package ru.zfix27r.data.local.db

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
import ru.zfix27r.data.model.db.*

// TODO #4 избавиться от воркера и зависимостей
@HiltWorker
class AppDatabaseWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val dao: WorkerDao
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.Main) {

        try {
            applicationContext.assets.open(ASSET_TOPIC_FILEPATH)
                .use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val type = object : TypeToken<List<TopicDbEntity>>() {}.type
                        val list: List<TopicDbEntity> = Gson().fromJson(jsonReader, type)
                        dao.setAllTopic(list)
                        Result.success()
                    }
                }

            applicationContext.assets.open(ASSET_QUOTE_FILEPATH)
                .use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val type = object : TypeToken<List<QuoteDbEntity>>() {}.type
                        val list: List<QuoteDbEntity> = Gson().fromJson(jsonReader, type)
                        dao.setAllQuote(list)
                        Result.success()
                    }
                }

            applicationContext.assets.open(ASSET_TOPIC_QUOTE_CROSS_REF_FILEPATH)
                .use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val type = object : TypeToken<List<TopicQuoteCrossRefDbEntity>>() {}.type
                        val list: List<TopicQuoteCrossRefDbEntity> = Gson().fromJson(jsonReader, type)
                        dao.setAllTopicQuoteCrossRef(list)
                        Result.success()
                    }
                }

            applicationContext.assets.open(ASSET_SOURCE_FILEPATH)
                .use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val type = object : TypeToken<List<SourceDbEntity>>() {}.type
                        val list: List<SourceDbEntity> = Gson().fromJson(jsonReader, type)
                        dao.setAllSource(list)
                        Result.success()
                    }
                }

            applicationContext.assets.open(ASSET_QUESTION_FILEPATH)
                .use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val type = object : TypeToken<List<QuestionDbEntity>>() {}.type
                        val list: List<QuestionDbEntity> = Gson().fromJson(jsonReader, type)
                        dao.setAllQuestion(list)
                        Result.success()
                    }
                }

        } catch (ex: Exception) {
            Result.failure()
        }
    }

    companion object {
        const val ASSET_TOPIC_FILEPATH = "topic.json"
        const val ASSET_QUOTE_FILEPATH = "quote.json"
        const val ASSET_SOURCE_FILEPATH = "source.json"
        const val ASSET_QUESTION_FILEPATH = "question.json"
        const val ASSET_TOPIC_QUOTE_CROSS_REF_FILEPATH = "topic_quote_cross_ref.json"
    }
}