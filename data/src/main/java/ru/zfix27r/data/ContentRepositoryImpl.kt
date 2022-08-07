package ru.zfix27r.data

import androidx.lifecycle.LiveData
import ru.zfix27r.data.common.ApiResponse
import ru.zfix27r.data.common.NetworkBoundResource
import ru.zfix27r.data.common.RateLimiter
import ru.zfix27r.data.common.Resource
import ru.zfix27r.data.local.db.ContentDao
import ru.zfix27r.data.model.db.Content
import ru.zfix27r.domain.repository.ContentRepository
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    val appExecutors: AppExecutors,
    val dao: ContentDao
) : ContentRepository {
    private val repoListRateLimit = RateLimiter<String>(10, TimeUnit.MINUTES)

    override fun getContent(id: Long): LiveData<Resource<Content>> {
        return object : NetworkBoundResource<Content, Content>(appExecutors) {
            override fun shouldFetch(data: Content?): Boolean {
                return data == null
            }

            override fun loadFromDb(): LiveData<Content> {
                return dao.getContent(id)
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
}