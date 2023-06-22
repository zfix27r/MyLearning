package ru.zfix27r.domain

import kotlinx.coroutines.flow.Flow
import ru.zfix27r.domain.main.GetTopicsByParentIdModel

interface MainRepository {
    fun getTopicsByParentId(topicParentId: Int): Flow<List<GetTopicsByParentIdModel>>
    fun deleteTopic(topicId: Int): Flow<Unit>
}