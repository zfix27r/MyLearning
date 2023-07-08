package ru.zfix27r.mylearning.ui.quote

data class QuoteSaveModel(
    val id: Int?,

    var description: String = "",

    var topicId: Int = 0,
    var topicIconId: Int = 0,
    var topicTitle: String = "",
    var topicSubtitle: String = "",
)