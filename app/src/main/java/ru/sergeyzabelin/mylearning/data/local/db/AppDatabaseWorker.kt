package ru.sergeyzabelin.mylearning.data.local.db

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sergeyzabelin.mylearning.data.model.db.Article
import ru.sergeyzabelin.mylearning.data.model.db.ArticleTagCrossRef
import ru.sergeyzabelin.mylearning.data.model.db.Tag
import ru.sergeyzabelin.mylearning.data.model.db.Topic
import ru.sergeyzabelin.mylearning.utils.AppConstants

class AppDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    //private val dao = AppDatabase.getInstance(applicationContext).databaseWorkerDao()

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            applicationContext.assets.open(AppConstants.ASSET_TOPIC_FILEPATH)
                .use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val type = object : TypeToken<List<Topic>>() {}.type
                        val list: List<Topic> = Gson().fromJson(jsonReader, type)
                        //dao.setAllTopic(list)
                        Result.success()
                    }
                }

            applicationContext.assets.open(AppConstants.ASSET_ARTICLE_FILEPATH)
                .use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val type = object : TypeToken<List<Article>>() {}.type
                        val list: List<Article> = Gson().fromJson(jsonReader, type)
                        //dao.setAllArticle(list)
                        Result.success()
                    }
                }

            applicationContext.assets.open(AppConstants.ASSET_TAG_FILEPATH)
                .use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val type = object : TypeToken<List<Tag>>() {}.type
                        val list: List<Tag> = Gson().fromJson(jsonReader, type)
                       //dao.setAllTag(list)
                        Result.success()
                    }
                }

            applicationContext.assets.open(AppConstants.ASSET_ARTICLE_TAG_CROSS_REF_FILEPATH)
                .use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val type = object : TypeToken<List<ArticleTagCrossRef>>() {}.type
                        val list: List<ArticleTagCrossRef> = Gson().fromJson(jsonReader, type)
                       // dao.setAllArticleTagCrossRef(list)
                        Result.success()
                    }
                }

        } catch (ex: Exception) {
            Result.failure()
        }
    }
}