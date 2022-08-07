package ru.zfix27r.data

import ru.zfix27r.data.local.db.QuoteDao
import ru.zfix27r.data.model.db.Quote
import ru.zfix27r.domain.repository.QuoteRepository
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    private val dao: QuoteDao
) : QuoteRepository {
    override suspend fun insert(quote: Quote): Long = dao.insert(quote)
    override suspend fun update(quote: Quote): Int = dao.update(quote)
    override suspend fun delete(quote: Quote): Int = dao.delete(quote)
}