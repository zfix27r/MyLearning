package ru.zfix27r.domain

import kotlinx.coroutines.flow.Flow
import ru.zfix27r.domain.quote.AddQuoteModel
import ru.zfix27r.domain.quote.SaveQuoteModel

interface QuoteRepository {
    fun addQuote(addQuoteModel: AddQuoteModel): Flow<Unit>
    fun saveQuote(saveQuoteModel: SaveQuoteModel): Flow<Unit>
    fun deleteQuote(id: Int): Flow<Unit>
}