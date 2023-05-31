package ru.sergeyzabelin.zfix27r.ui.dictionary


interface DictionaryActionListener {
    fun onSelf(topicId: Long)
    fun onDetails(topicId: Long)
}