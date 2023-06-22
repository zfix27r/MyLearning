package ru.zfix27r.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.zfix27r.data.local.dao.MainDao
import ru.zfix27r.data.local.entity.TopicEntity
import ru.zfix27r.domain.MainRepository
import ru.zfix27r.domain.main.GetTopicsByParentIdModel
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val dao: MainDao,
) : MainRepository {
    override fun getTopicsByParentId(topicParentId: Int) =
        dao.getTopicsByParentId(topicParentId).toListGetTopicModel()

    override fun deleteTopic(topicId: Int): Flow<Unit> {
        TODO("Not yet implemented")
    }

    private fun Flow<List<TopicEntity>>.toListGetTopicModel() =
        map { l ->
            l.map {
                it.run { GetTopicsByParentIdModel(id, title, subtitle, difficulty) }
            }
        }
}