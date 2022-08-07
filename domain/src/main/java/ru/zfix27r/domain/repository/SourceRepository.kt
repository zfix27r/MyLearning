package ru.zfix27r.domain.repository

import ru.zfix27r.data.model.db.Source

interface SourceRepository {
    suspend fun insert(source: Source): Long
    suspend fun update(source: Source): Int
    suspend fun delete(source: Source): Int
}