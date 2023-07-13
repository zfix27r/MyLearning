package ru.zfix27r.data.local.model.activity

import androidx.room.ColumnInfo
import ru.zfix27r.data.local.entity.TopicEntity

data class SearchDataModel(
    @ColumnInfo(name = TopicEntity.ID)
    val id: Int,
    @ColumnInfo(name = TopicEntity.ICON_ID)
    val iconId: Int?,
    @ColumnInfo(name = TopicEntity.TITLE)
    val title: String,
    @ColumnInfo(name = TopicEntity.SUBTITLE)
    val subtitle: String?,
)