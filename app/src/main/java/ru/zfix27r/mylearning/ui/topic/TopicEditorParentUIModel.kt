package ru.zfix27r.mylearning.ui.topic

data class TopicEditorParentUIModel(
    val id: Int,
    val parentId: Int,

    val title: String,
    val subtitle: String?,

    var isChecked: Boolean,
)