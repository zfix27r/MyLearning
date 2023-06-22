package ru.zfix27r.data.local.model

import androidx.room.ColumnInfo
import ru.zfix27r.data.local.entity.TopicEntity

data class DeleteTopicModel(
    @ColumnInfo(name = TopicEntity.ID)
    val id: Int,
)