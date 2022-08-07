package ru.zfix27r.data

import androidx.lifecycle.LiveData
import ru.zfix27r.data.common.ApiResponse
import ru.zfix27r.data.common.NetworkBoundResource
import ru.zfix27r.data.common.RateLimiter
import ru.zfix27r.data.common.Resource
import ru.zfix27r.data.local.db.DictionaryDao
import ru.zfix27r.data.model.db.Dictionary
import ru.zfix27r.domain.repository.DictionaryRepository
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DictionaryRepositoryImpl @Inject constructor(
    private val appExecutors: AppExecutors,
    private val dao: DictionaryDao
) : DictionaryRepository {

    private val repoListRateLimit = RateLimiter<String>(10, TimeUnit.MINUTES)

    override fun getDictionary(id: Long): LiveData<Resource<Dictionary>> {
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
                return dao.getDictionary(id)
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
}