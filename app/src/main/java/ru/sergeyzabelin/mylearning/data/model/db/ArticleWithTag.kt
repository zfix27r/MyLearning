package ru.sergeyzabelin.mylearning.data.model.db

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class ArticleWithTag(
    @Embedded
    val article: Article,

    @Relation(
        entity = Tag::class,
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = ArticleTagCrossRef::class,
            parentColumn = "articleId",
            entityColumn = "tagId"
        )
    )
    val tags: List<Tag>
)
