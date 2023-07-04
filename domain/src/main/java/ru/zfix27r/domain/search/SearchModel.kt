package ru.zfix27r.domain.search

sealed class SearchModel(open val id: Int) {
    data class Topic(
        override val id: Int,
        val title: String,
        val subtitle: String?,
    ) : SearchModel(id)

}
