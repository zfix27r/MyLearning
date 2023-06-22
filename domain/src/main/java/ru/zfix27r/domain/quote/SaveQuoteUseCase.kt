package ru.zfix27r.domain.quote

import ru.zfix27r.domain.QuoteRepository
import javax.inject.Inject

class SaveQuoteUseCase @Inject constructor(private val repository: QuoteRepository) {
    fun execute(saveQuoteModel: SaveQuoteModel) = repository.saveQuote(saveQuoteModel)
}