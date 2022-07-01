package ru.sergeyzabelin.mylearning.domain.model

data class SaveTopicModel(
    val topicParentId: Long,
    var title: String = "",
    var label: String = ""
)