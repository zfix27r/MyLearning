package ru.zfix27r.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.zfix27r.data.local.dao.MainDao
import ru.zfix27r.data.local.entity.QuoteEntity
import ru.zfix27r.data.local.entity.TopicEntity
import ru.zfix27r.domain.MainRepository
import ru.zfix27r.domain.main.GetMainModel
import ru.zfix27r.domain.search.SearchModel
import ru.zfix27r.domain.main.GetMainQuotesModel
import ru.zfix27r.domain.main.GetMainTopicsModel
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val dao: MainDao,
) : MainRepository {
    override fun getMain(topicId: Int) = dao.getMain(topicId).map {
        it?.toGetMainModel()
    }

    override fun getTopics(topicId: Int) =
        dao.getTopics(topicId).map { it.toListGetMainTopicsModel() }

    override fun getQuotes(topicId: Int) =
        dao.getQuotes(topicId).map { it.toListGetMainQuotesModel() }

    override fun search(string: String): Flow<SearchModel> {
        TODO("Not yet implemented")
    }

    private fun TopicEntity.toGetMainModel() = GetMainModel(id, title, subtitle)

    private fun List<TopicEntity>.toListGetMainTopicsModel() =
        map { GetMainTopicsModel(it.id, it.iconId, it.title, it.subtitle) }

    private fun List<QuoteEntity>.toListGetMainQuotesModel() =
        map { GetMainQuotesModel(it.id, it.description) }
}