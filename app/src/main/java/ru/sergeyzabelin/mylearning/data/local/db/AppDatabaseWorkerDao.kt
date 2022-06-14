package ru.sergeyzabelin.mylearning.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import ru.sergeyzabelin.mylearning.data.model.db.Article
import ru.sergeyzabelin.mylearning.data.model.db.ArticleTagCrossRef
import ru.sergeyzabelin.mylearning.data.model.db.Tag
import ru.sergeyzabelin.mylearning.data.model.db.Topic

@Dao
interface AppDatabaseWorkerDao {

    @Insert
    suspend fun setAllTopic(list: List<Topic>)

    @Insert
    suspend fun setAllArticle(list: List<Article>)

    @Insert
    suspend fun setAllTag(list: List<Tag>)

    @Insert
    suspend fun setAllArticleTagCrossRef(list: List<ArticleTagCrossRef>)
}