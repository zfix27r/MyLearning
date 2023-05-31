package ru.zfix27r.data.local.db

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import ru.zfix27r.data.local.entity.QuestionEntity
import ru.zfix27r.data.local.entity.QuoteEntity
import ru.zfix27r.data.local.entity.TopicEntity
import ru.zfix27r.domain.model.content.ContentDataModel

data class ContentDb(
    @Embedded
    val topic: ContentDataModel.Topic,

    @Relation(
        entity = QuoteEntity::class,
        parentColumn = TopicEntity.ID,
        entityColumn = QuoteEntity.ID_NAME,
        associateBy = Junction(
            value = TopicQuoteCrossRefDbEntity::class,
            parentColumn = TopicQuoteCrossRefDbEntity.TOPIC_ID,
            entityColumn = TopicQuoteCrossRefDbEntity.QUOTE_ID
        )
    )
    val quotes: List<QuoteWithSourceDb>,

    @Relation(
        entity = QuestionEntity::class,
        parentColumn = TopicEntity.ID,
        entityColumn = QuestionEntity.TOPIC_ID
    )
    val questions: List<ContentDataModel.Question>
) {

    fun toContent(): ContentDataModel {
        val topic = ContentDataModel.Topic(
            id = this.topic.id,
            title = this.topic.title,
            subtitle = this.topic.subtitle
        )

        val quotes = this.quotes.map {
            ContentDataModel.QuoteWithSource(
                ContentDataModel.QuoteWithSource.Quote(
                    id = it.quote.id,
                    sourceId = it.quote.sourceId,
                    text = it.quote.text,
                    usability = it.quote.usability
                ),
                ContentDataModel.QuoteWithSource.Source(
                    title = it.source.title,
                    year = it.source.year,
                    url = it.source.url
                )
            )
        }

        val questions = this.questions.map {
            ContentDataModel.Question(id = it.id, text = it.text)
        }

        return ContentDataModel(topic = topic, quotes = quotes, questions = questions)
    }
}