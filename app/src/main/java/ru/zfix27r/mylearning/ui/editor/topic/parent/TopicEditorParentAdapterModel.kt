package ru.zfix27r.mylearning.ui.editor.topic.parent

data class TopicEditorParentAdapterModel(
    val id: Int,
    val parentId: Int,

    val title: String,
    val subtitle: String,
    val childCount: Int,

    var isEditable: Boolean,
    var isSelect: Boolean,
)