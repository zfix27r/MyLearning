package ru.zfix27r.domain.repository

import ru.zfix27r.data.model.db.Quote

interface QuoteRepository {
    suspend fun insert(quote: Quote): Long
    suspend fun update(quote: Quote): Int
    suspend fun delete(quote: Quote): Int
}