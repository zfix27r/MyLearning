package ru.sergeyzabelin.mylearning.data.model.db

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class Dictionary(
    @Embedded
    val topic: Topic,

    @Relation(
        entity = Article::class,
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = TopicArticleCrossRef::class,
            parentColumn = "topicId",
            entityColumn = "articleId"
        )
    )
    var articles: List<Article>
)