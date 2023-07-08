package ru.zfix27r.mylearning.ui.topic.parent

interface TopicParentAdapterCallback {
    fun onClick(parent: TopicParentAdapterModel)
    fun onLongClick(topicId: Int)
    fun onClickForward(topicId: Int)
}