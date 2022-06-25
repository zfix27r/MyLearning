package ru.sergeyzabelin.mylearning.ui.dictionary

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import ru.sergeyzabelin.mylearning.data.model.db.Article
import ru.sergeyzabelin.mylearning.data.model.db.ArticleWithTopicLabel
import ru.sergeyzabelin.mylearning.databinding.ItemDictionaryArticleBinding

class DictionaryArticleAdapter(
    private val onClickGoWeb: ((Article) -> Unit)
) :
    ListAdapter<ArticleWithTopicLabel, RecyclerView.ViewHolder>(DiffCallback()) {
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

        fun bind(data: ArticleWithTopicLabel) {
            binding.article = data.article

            data.topics.forEach {
                val chip = Chip(context)
                chip.text = it.label.ifEmpty { it.title }

                binding.itemDictionaryArticleTags.addView(chip)
            }
            //binding.dictionaryTopicItem.setOnClickListener { onClickGoNext(topic) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ArticleWithTopicLabel>() {
        override fun areItemsTheSame(oldItem: ArticleWithTopicLabel, newItem: ArticleWithTopicLabel): Boolean {
            return oldItem.article.id == newItem.article.id
        }

        override fun areContentsTheSame(oldItem: ArticleWithTopicLabel, newItem: ArticleWithTopicLabel): Boolean {
            return oldItem == newItem
        }
    }
}
