package ru.zfix27r.domain

import kotlinx.coroutines.flow.Flow
import ru.zfix27r.domain.source.AddSourceModel
import ru.zfix27r.domain.source.SaveSourceModel

interface SourceRepository {
    fun addSource(addSourceModel: AddSourceModel): Flow<Unit>
    fun saveSource(saveSourceModel: SaveSourceModel): Flow<Unit>
    fun deleteSource(id: Int): Flow<Unit>
}