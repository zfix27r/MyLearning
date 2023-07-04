package ru.zfix27r.mylearning.ui.editor.topic.parent

interface TopicEditorParentAdapterCallback {
    fun onClickNext(topicId: Int)
    fun onClick(parent: TopicEditorParentAdapterModel)
}