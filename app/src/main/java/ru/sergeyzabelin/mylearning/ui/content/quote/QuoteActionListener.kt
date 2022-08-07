package ru.sergeyzabelin.mylearning.ui.content.quote

import ru.zfix27r.data.model.db.Quote
import ru.zfix27r.data.model.db.Source

interface QuoteActionListener {
    fun onUrlOpen(source: Source)

    fun onQuoteAdd(quote: Quote)
    fun onSourceAdd(source: Source)

    fun onQuoteEdit(quote: Quote)
    fun onSourceEdit(source: Source)

    fun onQuoteDelete(quote: Quote)
    fun onSourceDelete(source: Source)
}