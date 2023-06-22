package ru.zfix27r.mylearning.ui.topic

data class TopicEditorSaveModel(
    var id: Int? = null,

    var title: String = "",
    var subtitle: String? = null,
    var difficulty: Int? = null,

    var parentId: Int? = null,
    var parentTitle: String? = null,
    var parentSubtitle: String? = null,

    var isTitleCorrectly: Boolean = false,
)