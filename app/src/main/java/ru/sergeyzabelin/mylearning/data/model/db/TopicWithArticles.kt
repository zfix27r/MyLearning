package ru.sergeyzabelin.mylearning.data.model.db

import androidx.room.Embedded
import androidx.room.Relation

data class TopicWithArticles(
    @Embedded
    val topic: Topic,
    @Relation(parentColumn = "id", entity = Article::class, entityColumn = "topic_id")
    val articles: List<Article>
)
