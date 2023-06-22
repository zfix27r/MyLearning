package ru.zfix27r.domain.quote

import ru.zfix27r.domain.QuoteRepository
import javax.inject.Inject

class AddQuoteUseCase @Inject constructor(private val repository: QuoteRepository) {
    fun execute(addQuoteModel: AddQuoteModel) = repository.addQuote(addQuoteModel)
}
