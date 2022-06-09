package ru.sergeyzabelin.mylearning.ui.dictionary.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.sergeyzabelin.mylearning.data.model.db.Article
import ru.sergeyzabelin.mylearning.databinding.ItemDetailDictionaryArticleViewBinding

class ArticleAdapter(
    private val onClickViewWebsite: ((String) -> Unit)
) :
    ListAdapter<Article, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ItemDetailDictionaryArticleViewBinding.inflate(
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

    inner class ViewHolder(itemDetailDictionaryArticleViewBinding: ItemDetailDictionaryArticleViewBinding) :
        RecyclerView.ViewHolder(itemDetailDictionaryArticleViewBinding.root) {

        private val binding = itemDetailDictionaryArticleViewBinding

        fun bind(article: Article) {
            binding.article = article
            //binding.dictionaryDetailLinkItem.setOnClickListener { onClickViewWebsite(article.url) }
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
