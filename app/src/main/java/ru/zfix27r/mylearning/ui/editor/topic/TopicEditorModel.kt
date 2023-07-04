package ru.zfix27r.mylearning.ui.editor.topic

import ru.zfix27r.mylearning.ui.editor.EMPTY

data class TopicEditorModel(
    val id: Int?,
    var icon: Int = 0,
    var title: String = "",
    var subtitle: String = "",

    var parentId: Int = 0,
    var parentTitle: String = "",
    var parentSubtitle: String = "",

    var statusTitle: Int = EMPTY,
)