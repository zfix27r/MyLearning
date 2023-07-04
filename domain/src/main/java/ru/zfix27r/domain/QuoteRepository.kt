package ru.zfix27r.domain

import kotlinx.coroutines.flow.Flow
import ru.zfix27r.domain.editor.quote.GetQuoteEditorModel
import ru.zfix27r.domain.editor.quote.SaveQuoteEditorModel

interface QuoteRepository {
    fun getQuoteEditorModel(quoteId: Int): Flow<GetQuoteEditorModel>
    fun saveQuoteEditorModel(saveQuoteEditorModel: SaveQuoteEditorModel): Flow<Unit>
}