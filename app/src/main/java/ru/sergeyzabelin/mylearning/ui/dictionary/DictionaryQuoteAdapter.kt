package ru.sergeyzabelin.mylearning.ui.dictionary

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.sergeyzabelin.mylearning.data.model.db.Quote
import ru.sergeyzabelin.mylearning.databinding.ItemDictionaryQuoteBinding

class DictionaryQuoteAdapter(
    private val onClickGoWeb: ((String) -> Unit)? = null
) :
    ListAdapter<Quote, RecyclerView.ViewHolder>(DiffCallback()) {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context

        return ViewHolder(
            ItemDictionaryQuoteBinding.inflate(
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

    inner class ViewHolder(itemDictionaryQuoteBinding: ItemDictionaryQuoteBinding) :
        RecyclerView.ViewHolder(itemDictionaryQuoteBinding.root) {

        private val binding = itemDictionaryQuoteBinding

        fun bind(quote: Quote) {
            binding.quote = quote

            binding.text.text = Html.fromHtml("", Html.FROM_HTML_MODE_COMPACT)

/*            //TODO найти более подходящее место для изменение данных
            if (article.sourceUrl.isNotEmpty()) {
                binding.urlDomain = getDomainFromUrl(article.sourceUrl)
            }
            binding.itemDictionaryArticle.setOnClickListener { onClickGoWeb(article.sourceUrl) }*/
        }

/*        private fun getDomainFromUrl(url: String): String {
            val regex = """http[s]?://([a-z]+\.?[A-z]+\.[A-z]+)/[A-z]+.*+""".toRegex()

            val matches = regex.find(url)
            val (domain) = matches!!.destructured

            return domain
        }*/
    }

    class DiffCallback : DiffUtil.ItemCallback<Quote>() {
        override fun areItemsTheSame(oldItem: Quote, newItem: Quote): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Quote, newItem: Quote): Boolean {
            return oldItem == newItem
        }
    }
}
