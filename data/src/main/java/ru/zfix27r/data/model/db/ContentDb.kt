package ru.zfix27r.data.model.db

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import ru.zfix27r.domain.model.content.ContentResModel

data class ContentDb(
    @Embedded
    val topic: ContentResModel.Success.Topic,

    @Relation(
        entity = QuoteDbEntity::class,
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = TopicQuoteCrossRefDbEntity::class,
            parentColumn = "topicId",
            entityColumn = "quoteId"
        )
    )
    val quotes: List<QuoteWithSourceDb>,

    @Relation(
        entity = QuestionDbEntity::class,
        parentColumn = "id",
        entityColumn = "topicId",
        projection = ["id", "text"]
    )
    val questions: List<ContentResModel.Success.Question>
)