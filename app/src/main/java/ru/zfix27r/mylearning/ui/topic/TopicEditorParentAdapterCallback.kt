package ru.zfix27r.mylearning.ui.topic

interface TopicEditorParentAdapterCallback {
    fun navigateToSelf(topicId: Int)
    fun onParentChecked(topic: TopicEditorParentUIModel)
}