package ru.zfix27r.domain.quote

import ru.zfix27r.domain.QuoteRepository
import javax.inject.Inject

class DeleteQuoteUseCase @Inject constructor(private val repository: QuoteRepository) {
    fun execute(id: Int) = repository.deleteQuote(id)
}