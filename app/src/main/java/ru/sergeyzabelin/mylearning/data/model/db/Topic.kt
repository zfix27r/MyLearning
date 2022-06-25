package ru.sergeyzabelin.mylearning.data.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Topic(

    @PrimaryKey(autoGenerate = true)
    val id: Long,

    // 0 - старт; остальное ид родителя
    val topicParentId: Long,
    // искать ли вло0 - нет детей; 1 - есть
    val isExistsTopicChild: Boolean,

    val title: String,
    val label: String,
    val wikiId: Long
    /*
    * счетчик просмотров \ популярность
    * Для формирования списков наиболеее просматриваемого и наименее
    * */

    /*
    * рейтинг полезности
    * возможность в ручную установить рейтинг полезности темы
    * */
)
