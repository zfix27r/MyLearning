package ru.sergeyzabelin.mylearning.data.model.db

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class ArticleWithTopicLabel(
    @Embedded
    val article: Article,

    @Relation(
        entity = Topic::class,
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = TopicArticleCrossRef::class,
            parentColumn = "articleId",
            entityColumn = "topicId"
        )
    )
    var topics: List<TopicLabel>
)