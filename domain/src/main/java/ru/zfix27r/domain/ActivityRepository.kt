package ru.zfix27r.domain

import kotlinx.coroutines.flow.Flow
import ru.zfix27r.domain.activity.SearchModel

interface ActivityRepository {
    fun search(query: String): Flow<List<SearchModel>>
}