package ru.sergeyzabelin.mylearning.ui.dictionary


interface DictionaryActionListener {
    fun onSelf(topicId: Long)
    fun onDetails(topicId: Long)
    fun onEdit(topicId: Long)
    fun onDelete(topicId: Long)
}