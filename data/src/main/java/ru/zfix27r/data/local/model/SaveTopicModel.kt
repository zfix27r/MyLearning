package ru.zfix27r.data.local.model

import androidx.room.ColumnInfo
import ru.zfix27r.data.local.entity.TopicEntity

data class SaveTopicModel(
    @ColumnInfo(name = TopicEntity.ID)
    val id: Int? = null,

    @ColumnInfo(name = TopicEntity.PARENT_ID)
    val parentId: Int? = null,

    @ColumnInfo(name = TopicEntity.TITLE)
    val title: String,

    @ColumnInfo(name = TopicEntity.SUBTITLE)
    val subtitle: String? = null,

    @ColumnInfo(name = TopicEntity.DIFFICULTY)
    val difficulty: Int? = null,
)