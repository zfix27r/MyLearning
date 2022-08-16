package ru.sergeyzabelin.mylearning.ui.content.quote


import ru.zfix27r.domain.model.content.ContentDataModel

interface QuoteActionListener {
    fun onUrlOpen(source: ContentDataModel.QuoteWithSource.Source)
}