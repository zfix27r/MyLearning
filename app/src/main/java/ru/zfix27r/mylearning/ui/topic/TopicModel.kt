package ru.zfix27r.mylearning.ui.topic


data class TopicModel(
    val id: Int?,
    var iconId: Int = 0,
    var title: String = "",
    var subtitle: String = "",

    var parentId: Int = 0,
    var parentTitle: String = "",
    var parentSubtitle: String = "",
)