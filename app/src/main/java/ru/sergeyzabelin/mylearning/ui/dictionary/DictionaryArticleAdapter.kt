package ru.sergeyzabelin.mylearning.ui.dictionary

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.sergeyzabelin.mylearning.data.model.db.Article
import ru.sergeyzabelin.mylearning.databinding.ItemDictionaryArticleBinding

class DictionaryArticleAdapter(
    private val onClickGoWeb: ((String) -> Unit)
) :
    ListAdapter<Article, RecyclerView.ViewHolder>(DiffCallback()) {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context

        return ViewHolder(
            ItemDictionaryArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ViewHolder
        viewHolder.bind(currentList[position])
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    inner class ViewHolder(itemDictionaryArticleBinding: ItemDictionaryArticleBinding) :
        RecyclerView.ViewHolder(itemDictionaryArticleBinding.root) {

        private val binding = itemDictionaryArticleBinding

        fun bind(article: Article) {
            binding.article = article

/*            //TODO найти более подходящее место для изменение данных
            if (article.sourceUrl.isNotEmpty()) {
                binding.urlDomain = getDomainFromUrl(article.sourceUrl)
            }
            binding.itemDictionaryArticle.setOnClickListener { onClickGoWeb(article.sourceUrl) }*/
        }

        private fun getDomainFromUrl(url: String): String {
            val regex = """http[s]?://([a-z]+\.?[A-z]+\.[A-z]+)/[A-z]+.*+""".toRegex()

            val matches = regex.find(url)
            val (domain) = matches!!.destructured

            return domain
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
}
