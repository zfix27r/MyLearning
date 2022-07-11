package ru.sergeyzabelin.mylearning.domain.model

data class SaveTopicModel(
    var id: Long = 0,
    var topicParentId: Long = 0,
    var title: String = "",
    var label: String = ""
)