package ru.sergeyzabelin.mylearning.ui.dictionary

import ru.sergeyzabelin.mylearning.data.model.db.Topic

interface TopicActionListener {

    fun onTopicNext(topic: Topic)

    fun onTopicDetails(topic: Topic)

    fun onTopicAdd(topic: Topic)

    fun onTopicEdit(topic: Topic)

    fun onTopicDelete(topic: Topic)
}