package ru.sergeyzabelin.mylearning.data.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Topic(
    @PrimaryKey(autoGenerate = true)
    val id: Long,

    val parentId: Long,

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
