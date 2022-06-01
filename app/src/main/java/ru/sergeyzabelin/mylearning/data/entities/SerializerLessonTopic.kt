package ru.sergeyzabelin.mylearning.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class SerializerLessonTopic(
    val title: String,
    val description: String,
    val contentPath: String
)
