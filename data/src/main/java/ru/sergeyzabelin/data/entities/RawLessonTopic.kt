package ru.sergeyzabelin.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class RawLessonTopic(
    val title: String,
    val description: String
)
