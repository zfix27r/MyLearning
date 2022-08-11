package ru.sergeyzabelin.mylearning.ui.content.quote


import ru.zfix27r.domain.model.quote.QuoteModel
import ru.zfix27r.domain.model.source.SourceModel

interface QuoteActionListener {
    fun onUrlOpen(source: SourceModel)

    fun onQuoteAdd(quote: QuoteModel)
    fun onSourceAdd(source: SourceModel)

    fun onQuoteEdit(quote: QuoteModel)
    fun onSourceEdit(source: SourceModel)

    fun onQuoteDelete(quote: QuoteModel)
    fun onSourceDelete(source: SourceModel)
}