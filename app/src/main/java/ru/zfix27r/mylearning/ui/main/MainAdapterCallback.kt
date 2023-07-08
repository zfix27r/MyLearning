package ru.zfix27r.mylearning.ui.main

interface MainAdapterCallback {
    fun onClick(quoteId: Int)
    fun onLongClick(quoteId: Int)
    fun onClickMore(quoteId: Int)
}