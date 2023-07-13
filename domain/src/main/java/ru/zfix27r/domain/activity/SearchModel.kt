package ru.zfix27r.domain.activity

sealed class SearchModel(open val id: Int) {
    data class Topic(
        override val id: Int,
        val iconId: Int?,
        val title: String,
        val subtitle: String?,
    ) : SearchModel(id)

    data class Quote(
        override val id: Int,
    ) : SearchModel(id)

    data class Source(
        override val id: Int
    ) : SearchModel(id)
}
