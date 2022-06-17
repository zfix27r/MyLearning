package ru.sergeyzabelin.mylearning.data

import androidx.lifecycle.LiveData
import ru.sergeyzabelin.mylearning.data.local.db.TopicDao
import ru.sergeyzabelin.mylearning.data.model.db.Topic
import ru.sergeyzabelin.mylearning.utils.AppExecutors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DictionaryRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val dao: TopicDao
) {

/*    private val repoListRateLimit = RateLimiter<String>(10, TimeUnit.MINUTES)*/

    fun getTopicByParentId(parentId: Long): LiveData<Resource<List<Topic>>> {
        return object : NetworkBoundResource<List<Topic>, List<Topic>>(appExecutors) {
/*            override fun saveCallResult(item: List<Topic>) {
*//*                repoDao.insertRepos(item) TODO why*//*
            }*/

            override fun shouldFetch(data: List<Topic>?): Boolean {
                return data == null || data.isEmpty()/* || repoListRateLimit.shouldFetch(parentId.toString())*/
            }

            override fun loadFromDb() = dao.getTopicByParentId(parentId)

            //override fun createCall() = githubService.getRepos(owner)

/*            override fun onFetchFailed() {
                repoListRateLimit.reset(parentId.toString())
            }*/
        }.asLiveData()
    }
}