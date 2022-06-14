package ru.sergeyzabelin.mylearning.ui.dictionary.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import ru.sergeyzabelin.mylearning.R
import ru.sergeyzabelin.mylearning.data.model.db.Article
import ru.sergeyzabelin.mylearning.databinding.ItemDetailDictionaryArticleBinding


class ArticleAdapter(
    private val onClickViewWebsite: ((String) -> Unit),
    private val onRefreshDescription: ((Article) -> Unit)
) :
    ListAdapter<Article, RecyclerView.ViewHolder>(DiffCallback()) {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context

        return ViewHolder(
            ItemDetailDictionaryArticleBinding.inflate(
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

    inner class ViewHolder(itemDetailDictionaryArticleBinding: ItemDetailDictionaryArticleBinding) :
        RecyclerView.ViewHolder(itemDetailDictionaryArticleBinding.root) {

        private val binding = itemDetailDictionaryArticleBinding

        fun bind(article: Article) {
            binding.detailDictionaryExpander.setOnClickListener {
                TransitionManager.beginDelayedTransition(
                    binding.detailDictionaryItem,
                    AutoTransition()
                )

                if (binding.detailDictionaryDescription.isVisible) {
                    binding.detailDictionaryExpander.background = AppCompatResources.getDrawable(
                        context,
                        R.drawable.ic_baseline_expand_more_24
                    )
                    binding.detailDictionaryDescription.visibility = View.GONE
                    binding.detailDictionaryTags.visibility = View.VISIBLE
                } else {
                    binding.detailDictionaryExpander.background = AppCompatResources.getDrawable(
                        context,
                        R.drawable.ic_baseline_expand_less_24
                    )
                    binding.detailDictionaryDescription.visibility = View.VISIBLE
                    binding.detailDictionaryTags.visibility = View.GONE
                }
            }

            if (article.description.isEmpty()) {
                binding.root.visibility = View.GONE
                onRefreshDescription(article)
            } else {
                binding.article = article

                //binding.dictionaryDetailLinkItem.setOnClickListener { onClickViewWebsite(article.url) }
            }
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
