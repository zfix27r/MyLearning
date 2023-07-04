package ru.zfix27r.mylearning.ui.search

sealed class SearchAdapterModel(open val id: Int) {
    data class Topic(
        override val id: Int,
        val title: String,
        val subtitle: String?,
    ) : SearchAdapterModel(id)
}