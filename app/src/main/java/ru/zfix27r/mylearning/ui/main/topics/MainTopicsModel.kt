package ru.zfix27r.mylearning.ui.main.topics

sealed class MainTopicsModel(open val id: Int) {
    data class Topic(
        override val id: Int,
        val icon: Int?,
        val title: String,
        val subtitle: String?,
    ) : MainTopicsModel(id)

    data class Empty(
        override val id: Int = 0,
    ) : MainTopicsModel(id)
}