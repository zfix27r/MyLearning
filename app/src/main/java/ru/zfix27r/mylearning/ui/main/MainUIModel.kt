package ru.zfix27r.mylearning.ui.main

sealed class MainUIModel(
    open val id: Int,
) {
    data class Topic(
        override val id: Int,
        val title: String,
        val subtitle: String?,
        val difficulty: Int?,

        var isBusy: Boolean = false,
    ) : MainUIModel(id)

    data class Add(
        override val id: Int = -1
    ) : MainUIModel(id)
}