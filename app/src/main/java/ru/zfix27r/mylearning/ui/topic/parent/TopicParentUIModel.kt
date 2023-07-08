package ru.zfix27r.mylearning.ui.topic.parent

data class TopicParentUIModel(
    var topicId: Int,
    var topicParentId: Int,

    val navQueue: MutableList<Int> = mutableListOf(),

    var isEditing: Boolean = false,
)