package ru.sergeyzabelin.mylearning.data

import ru.sergeyzabelin.mylearning.data.local.db.DictionaryDao
import ru.sergeyzabelin.mylearning.data.model.api.WikiDetail
import ru.sergeyzabelin.mylearning.data.model.db.Article
import ru.sergeyzabelin.mylearning.utils.AppExecutors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailDictionaryRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val dao: DictionaryDao
) {

    //fun getTopicWithArticlesById(id: Long) = dao.getTopicWithArticlesById(id)

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

        return WikiDetail()
/*        return apiBuilder.getClient(RETROFIT_WIKI_BASE_URL)
            .create(WikiApi::class.java)
            .getDetail(queryMap)*/
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