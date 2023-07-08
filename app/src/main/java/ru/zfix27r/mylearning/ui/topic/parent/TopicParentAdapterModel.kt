package ru.zfix27r.mylearning.ui.topic.parent

data class TopicParentAdapterModel(
    val id: Int,
    val parentId: Int,

    val title: String,
    val subtitle: String,
    val childCount: Int,

    val isEditable: Boolean,
    val isSelect: Boolean,
    var isShowEditUI: Boolean,
)