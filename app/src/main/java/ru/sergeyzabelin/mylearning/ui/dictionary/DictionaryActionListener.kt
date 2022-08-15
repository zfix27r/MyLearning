package ru.sergeyzabelin.mylearning.ui.dictionary


interface DictionaryActionListener {
    fun onSelf(topicId: Long)
    fun onDetails(topicId: Long)
}