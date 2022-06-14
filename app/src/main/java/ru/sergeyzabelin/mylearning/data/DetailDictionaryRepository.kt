package ru.sergeyzabelin.mylearning.data

import ru.sergeyzabelin.mylearning.data.local.db.TopicDao
import ru.sergeyzabelin.mylearning.data.model.api.WikiDetail
import ru.sergeyzabelin.mylearning.data.model.db.Article
import ru.sergeyzabelin.mylearning.data.remote.WikiApi
import ru.sergeyzabelin.mylearning.utils.ApiBuilder
import ru.sergeyzabelin.mylearning.utils.AppConstants.Companion.RETROFIT_WIKI_BASE_URL

class DetailDictionaryRepository(private val apiBuilder: ApiBuilder, private val dao: TopicDao) {

    fun getTopicWithArticlesById(id: Long) = dao.getTopicWithArticlesById(id)

    suspend fun getDescriptionById(id: Int): WikiDetail {
        val queryMap: Map<String, String> = mapOf(
            Pair("action", "query"),
            Pair("prop", "extracts"),
            Pair("exintro", "1"),
            Pair("explaintext", "1"),
            Pair("format", "json"),
            Pair("formatversion", "2"),
            Pair("pageids", id.toString())
        )

        return apiBuilder.getClient(RETROFIT_WIKI_BASE_URL)
            .create(WikiApi::class.java)
            .getDetail(queryMap)
    }
    /*{

        val re = service.getTodo(1)
        Log.e("sdf", re.toString())

*//*        RetrofitClient.getClient(AppConstants.RETROFIT_WIKI_BASE_URL)
            .create(WikiApi::class.java)
            .getDescriptionById()*//*
    }*/

    suspend fun setArticle(article: Article) = dao.setArticle(article)
}