package ru.sergeyzabelin.mylearning.data

import androidx.lifecycle.LiveData
import ru.sergeyzabelin.mylearning.data.common.NetworkBoundResource
import ru.sergeyzabelin.mylearning.data.common.RateLimiter
import ru.sergeyzabelin.mylearning.data.common.Resource
import ru.sergeyzabelin.mylearning.data.local.db.DictionaryDao
import ru.sergeyzabelin.mylearning.data.model.db.Article
import ru.sergeyzabelin.mylearning.data.model.db.Dictionary
import ru.sergeyzabelin.mylearning.data.model.db.Topic
import ru.sergeyzabelin.mylearning.domain.model.SaveTopicModel
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

    override fun getDictionaryBy(parentTopicId: Long): LiveData<Resource<List<Dictionary>>> {
        return object : NetworkBoundResource<List<Dictionary>, List<Dictionary>>(appExecutors) {
/*            override fun saveCallResult(item: List<Topic>) {
                //repoDao.insertRepos(item) TODO why
            }*/

            override fun shouldFetch(data: List<Dictionary>?): Boolean {
                return data == null /*|| repoListRateLimit.shouldFetch(id.toString())*/
            }

            override fun loadFromDb(): LiveData<List<Dictionary>> {

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
                return dao.getDictionaryBy(parentTopicId)
            }

            //override fun createCall() = githubService.getRepos(owner)

            override fun onFetchFailed() {
                repoListRateLimit.reset(parentTopicId.toString())
            }
        }.asLiveData()
    }

    override suspend fun saveDictionaryTopic(saveTopicModel: SaveTopicModel) {
        val topic = Topic(
                id = 0,
                parentTopicId = saveTopicModel.topicParentId,
                title = saveTopicModel.title,
                label = saveTopicModel.label
            )

        dao.setTopic(topic)
    }

    override suspend fun saveDictionaryArticle(article: Article) {
        dao.setArticle(article)
    }
}