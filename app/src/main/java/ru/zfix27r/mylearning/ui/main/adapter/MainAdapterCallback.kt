package ru.zfix27r.mylearning.ui.main.adapter

interface MainAdapterCallback {
    fun viewAdapterItemMenu(topicId: Int)
    fun navigateToSelf(topicParentId: Int)
    fun navigateToTopicAdd()
}