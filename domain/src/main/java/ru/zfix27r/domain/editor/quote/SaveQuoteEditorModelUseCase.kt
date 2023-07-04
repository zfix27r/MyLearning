package ru.zfix27r.domain.editor.quote

import ru.zfix27r.domain.QuoteRepository
import javax.inject.Inject

class SaveQuoteEditorModelUseCase @Inject constructor(private val repository: QuoteRepository) {
    fun execute(saveQuoteEditorModel: SaveQuoteEditorModel) =
        repository.saveQuoteEditorModel(saveQuoteEditorModel)
}