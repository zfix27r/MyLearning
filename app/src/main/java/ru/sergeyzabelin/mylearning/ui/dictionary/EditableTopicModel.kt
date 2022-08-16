package ru.sergeyzabelin.mylearning.ui.dictionary

data class EditableTopicModel(
    val id: Long,
    val parentId: Long,
    var title: String = "",
    var subtitle: String = "",
    var difficulty: Int = 0
)