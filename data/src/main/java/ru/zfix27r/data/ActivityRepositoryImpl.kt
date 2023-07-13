package ru.zfix27r.data

import kotlinx.coroutines.flow.map
import ru.zfix27r.data.local.dao.ActivityDao
import ru.zfix27r.data.local.model.activity.SearchDataModel
import ru.zfix27r.domain.ActivityRepository
import ru.zfix27r.domain.activity.SearchModel
import javax.inject.Inject

class ActivityRepositoryImpl @Inject constructor(
    private val dao: ActivityDao,
) : ActivityRepository {
    override fun search(query: String) = dao.search("%$query%").map { it.toListSearchModel() }

    private fun List<SearchDataModel>.toListSearchModel() =
        map {
            SearchModel.Topic(it.id, it.iconId, it.title, it.subtitle)
        }
}