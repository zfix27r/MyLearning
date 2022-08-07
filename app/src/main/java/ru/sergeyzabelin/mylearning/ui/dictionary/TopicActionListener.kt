package ru.sergeyzabelin.mylearning.ui.dictionary

import ru.zfix27r.data.model.db.Topic

interface TopicActionListener {

    fun onSelf(topic: Topic)

    fun onDetails(topic: Topic)

    fun onAdd(topic: Topic)

    fun onEdit(topic: Topic)

    fun onDelete(topic: Topic)
}