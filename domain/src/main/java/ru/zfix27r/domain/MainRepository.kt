package ru.zfix27r.domain

import kotlinx.coroutines.flow.Flow
import ru.zfix27r.domain.main.GetMainModel
import ru.zfix27r.domain.main.GetMainQuotesModel
import ru.zfix27r.domain.main.GetMainTopicsModel
import ru.zfix27r.domain.search.SearchModel

interface MainRepository {
    fun getMain(topicId: Int): Flow<GetMainModel?>
    fun getTopics(topicId: Int): Flow<List<GetMainTopicsModel>>
    fun getQuotes(topicId: Int): Flow<List<GetMainQuotesModel>>
    fun search(string: String): Flow<SearchModel>
}