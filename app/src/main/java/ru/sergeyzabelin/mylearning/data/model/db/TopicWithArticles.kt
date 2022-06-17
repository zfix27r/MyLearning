package ru.sergeyzabelin.mylearning.data.model.db

import androidx.room.Embedded
import androidx.room.Relation

data class TopicWithArticles(
    @Embedded
    val topic: Topic,

    @Relation(entity = Article::class, parentColumn = "id", entityColumn = "parentId")
    val articles: List<ArticleWithTag>
)