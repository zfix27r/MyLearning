package ru.sergeyzabelin.mylearning.data

import androidx.lifecycle.LiveData
import ru.sergeyzabelin.mylearning.data.common.ApiResponse
import ru.sergeyzabelin.mylearning.data.common.NetworkBoundResource
import ru.sergeyzabelin.mylearning.data.common.RateLimiter
import ru.sergeyzabelin.mylearning.data.common.Resource
import ru.sergeyzabelin.mylearning.data.local.db.DictionaryDao
import ru.sergeyzabelin.mylearning.data.model.db.*
import ru.sergeyzabelin.mylearning.domain.repository.DictionaryRepository
import ru.sergeyzabelin.mylearning.utils.AppExecutors
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DictionaryRepositoryImpl @Inject constructor(
    private val appExecutors: AppExecutors,
    private val dao: DictionaryDao
) : DictionaryRepository {

    private val repoListRateLimit = RateLimiter<String>(10, TimeUnit.MINUTES)

    override fun getDictionaryBy(id: Long): LiveData<Resource<Dictionary>> {
        return object : NetworkBoundResource<Dictionary, Dictionary>(appExecutors) {
/*            override fun saveCallResult(item: List<Topic>) {
                //repoDao.insertRepos(item) TODO why
            }*/

            override fun shouldFetch(data: Dictionary?): Boolean {
                return data == null /*|| repoListRateLimit.shouldFetch(id.toString())*/
            }

            override fun loadFromDb(): LiveData<Dictionary> {

                // TODO либо переделать на сырой зарос с исключением текущего топика, либо я хз
/*                return dao.getDictionaryDataById(id).let { liveData ->
                    liveData.value?.let { dictionaryData ->
                        dictionaryData.articles.forEach { articleWithTopicLabels ->
                            val topicLabelList =
                                articleWithTopicLabels.topics as MutableList<TopicLabel>

                            for (i in topicLabelList.indices) {
                                if (topicLabelList[i].id == dictionaryData.topic.id) {
                                    topicLabelList.removeAt(i)
                                    break
                                }
                            }

                            Log.e("dd", articleWithTopicLabels.topics.toString())
                            articleWithTopicLabels.topics = topicLabelList
                            Log.e("dd", articleWithTopicLabels.topics.toString())
                        }
                    }
                } as LiveData<DictionaryData>*/
                return dao.getDictionaryBy(id)
            }

            //override fun createCall() = githubService.getRepos(owner)

            override fun onFetchFailed() {
                repoListRateLimit.reset(id.toString())
            }

            override fun saveCallResult(item: Dictionary) {
            }

            override fun createCall(): LiveData<ApiResponse<Dictionary>>? {
                return null
            }

        }.asLiveData()
    }

    override fun getTopicBy(id: Long): LiveData<Resource<Topic>> {
        return object : NetworkBoundResource<Topic, Topic>(appExecutors) {
            override fun shouldFetch(data: Topic?): Boolean {
                return data == null
            }

            override fun loadFromDb(): LiveData<Topic> {
                return dao.getTopicBy(id)
            }

            override fun onFetchFailed() {
                repoListRateLimit.reset(id.toString())
            }

            override fun saveCallResult(item: Topic) {
            }

            override fun createCall(): LiveData<ApiResponse<Topic>>? {
                return null
            }
        }.asLiveData()
    }

    override fun getContentBy(id: Long): LiveData<Resource<Content>> {
        return object : NetworkBoundResource<Content, Content>(appExecutors) {
            override fun shouldFetch(data: Content?): Boolean {
                return data == null
            }

            override fun loadFromDb(): LiveData<Content> {
                return dao.getTopicWithQuoteBy(id)
            }

            override fun onFetchFailed() {
                repoListRateLimit.reset(id.toString())
            }

            override fun saveCallResult(item: Content) {
            }

            override fun createCall(): LiveData<ApiResponse<Content>>? {
                return null
            }
        }.asLiveData()
    }

    override suspend fun save(quote: Quote) = dao.save(quote)
    override suspend fun save(topic: Topic) = dao.save(topic)
    override suspend fun save(source: Source) = dao.save(source)
    override suspend fun save(question: Question) = dao.save(question)

    override suspend fun delete(quote: Quote) = dao.delete(quote)
    override suspend fun delete(topic: Topic) = dao.delete(topic)
    override suspend fun delete(source: Source) = dao.delete(source)
    override suspend fun delete(question: Question) = dao.delete(question)
}