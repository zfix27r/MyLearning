package ru.zfix27r.domain.usecases

import ru.zfix27r.data.ContentRepositoryImpl
import ru.zfix27r.data.model.db.Quote
import javax.inject.Inject

class SaveQuoteUseCase @Inject constructor(
    private val contentRepository: ContentRepositoryImpl
) {
    suspend fun execute(quote: Quote) {
/*        if (quote.id > 0) contentRepository.update(quote)
        else contentRepository.insert(quote)*/
    }
}