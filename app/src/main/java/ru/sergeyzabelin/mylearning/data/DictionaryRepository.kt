package ru.sergeyzabelin.mylearning.data

import ru.sergeyzabelin.mylearning.data.local.db.TopicDao

class DictionaryRepository(private val dao: TopicDao) {

    fun getTopicByParentId(parentId: Long) = dao.getTopicByParentId(parentId)
}