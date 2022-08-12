package ru.sergeyzabelin.mylearning.ui.dictionary.common

data class RawTopicModel(
    val id: Long,
    val parentId: Long,
    var title: String = "",
    var subTitle: String = "",
    var difficulty: Int = 0
)
