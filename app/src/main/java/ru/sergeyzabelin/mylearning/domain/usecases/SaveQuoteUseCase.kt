package ru.sergeyzabelin.mylearning.domain.usecases

import ru.sergeyzabelin.mylearning.data.DictionaryRepositoryImpl
import ru.sergeyzabelin.mylearning.data.model.db.Quote
import javax.inject.Inject

class SaveQuoteUseCase @Inject constructor(
    private val dictionaryRepository: DictionaryRepositoryImpl
) {
    suspend fun execute(quote: Quote) {
        if (quote.id > 0) dictionaryRepository.update(quote)
        else dictionaryRepository.insert(quote)
    }
}