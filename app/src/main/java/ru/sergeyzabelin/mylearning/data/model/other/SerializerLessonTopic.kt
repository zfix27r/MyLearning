package ru.sergeyzabelin.mylearning.data.model.other

import kotlinx.serialization.Serializable

@Serializable
data class SerializerLessonTopic(
    val title: String,
    val description: String,
    val contentPath: String
)
