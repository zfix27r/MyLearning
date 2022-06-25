package ru.sergeyzabelin.mylearning.data.model.db

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class DictionaryData(
    @Embedded
    val topic: Topic,

    @Relation(
        entity = Topic::class,
        parentColumn = "id",
        entityColumn = "topicParentId")
    val topics: List<Topic>,

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
    val articles: List<ArticleWithTopicLabel>
) {
    fun filter() {

    }
}