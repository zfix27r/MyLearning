package ru.zfix27r.data

import androidx.lifecycle.LiveData
import ru.zfix27r.data.common.ApiResponse
import ru.zfix27r.data.common.NetworkBoundResource
import ru.zfix27r.data.common.RateLimiter
import ru.zfix27r.data.common.Resource
import ru.zfix27r.data.local.db.TopicDao
import ru.zfix27r.data.model.db.Topic
import ru.zfix27r.domain.repository.TopicRepository
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class TopicRepositoryImpl @Inject constructor(
    private val appExecutors: AppExecutors,
    private val dao: TopicDao
) : TopicRepository {
    private val repoListRateLimit = RateLimiter<String>(10, TimeUnit.MINUTES)

    override fun getTopic(id: Long): LiveData<Resource<Topic>> {
        return object : NetworkBoundResource<Topic, Topic>(appExecutors) {
            override fun shouldFetch(data: Topic?): Boolean {
                return data == null
            }

            override fun loadFromDb(): LiveData<Topic> {
                return dao.getTopic(id)
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
    override suspend fun insert(topic: Topic): Long = dao.insert(topic)
    override suspend fun update(topic: Topic): Int = dao.update(topic)
    override suspend fun delete(topic: Topic): Int = dao.delete(topic)
}