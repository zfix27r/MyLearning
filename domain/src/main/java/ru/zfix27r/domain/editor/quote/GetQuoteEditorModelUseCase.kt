package ru.zfix27r.domain.editor.quote

import ru.zfix27r.domain.QuoteRepository
import javax.inject.Inject

class GetQuoteEditorModelUseCase @Inject constructor(private val repository: QuoteRepository) {
    fun execute(quoteId: Int) = repository.getQuoteEditorModel(quoteId)
}