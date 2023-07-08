package ru.zfix27r.data

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import ru.zfix27r.data.error.DBQueryError
import ru.zfix27r.data.local.dao.QuoteDao
import ru.zfix27r.data.local.model.GetQuoteEditorDataModel
import ru.zfix27r.data.local.model.SaveQuoteModel
import ru.zfix27r.domain.QuoteRepository
import ru.zfix27r.domain.editor.quote.GetQuoteEditorModel
import ru.zfix27r.domain.editor.quote.SaveQuoteEditorModel
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    private val dao: QuoteDao
) : QuoteRepository {
    override fun getQuoteEditorModel(quoteId: Int) =
        dao.getGetQuoteEditorDataModel(quoteId).map { it.toGetQuoteEditorModel() }

    override fun saveQuoteEditorModel(saveQuoteEditorModel: SaveQuoteEditorModel) = flow {
        val response = dao.saveQuote(saveQuoteEditorModel.toSaveQuoteModel())
        if (response > 0) emit(Unit)
        else throw DBQueryError("DB: $saveQuoteEditorModel \nResponse: $response")
    }

    private fun GetQuoteEditorDataModel.toGetQuoteEditorModel() =
        GetQuoteEditorModel(description, topicId, topicIconId, topicTitle, topicSubtitle)

    private fun SaveQuoteEditorModel.toSaveQuoteModel() = SaveQuoteModel(id, topicId, description)
}