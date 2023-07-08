package ru.zfix27r.domain

import kotlinx.coroutines.flow.Flow
import ru.zfix27r.domain.main.GetMainModel

interface MainRepository {
    fun getMainModels(): Flow<List<GetMainModel>>
}