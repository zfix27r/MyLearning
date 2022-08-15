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
        parentColumn = TopicDbEntity.ID,
        entityColumn = QuoteDbEntity.ID,
        associateBy = Junction(
            value = TopicQuoteCrossRefDbEntity::class,
            parentColumn = TopicQuoteCrossRefDbEntity.TOPIC_ID,
            entityColumn = TopicQuoteCrossRefDbEntity.QUOTE_ID
        )
    )
    val quotes: List<QuoteWithSourceDb>,

    @Relation(
        entity = QuestionDbEntity::class,
        parentColumn = TopicDbEntity.ID,
        entityColumn = QuestionDbEntity.TOPIC_ID
    )
    val questions: List<ContentResModel.Success.Question>
)