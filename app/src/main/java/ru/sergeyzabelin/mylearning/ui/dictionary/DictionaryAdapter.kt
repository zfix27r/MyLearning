package ru.sergeyzabelin.mylearning.ui.dictionary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.sergeyzabelin.mylearning.data.model.db.Topic
import ru.sergeyzabelin.mylearning.data.model.db.TopicWithQuote
import ru.sergeyzabelin.mylearning.databinding.ItemDictionaryBinding


class DictionaryAdapter(
    private val onClickGoNextTopic: ((Long) -> Unit),
    private val onClickGoTopicQuote: ((Long) -> Unit),
    private val onLongClickActionMode: ((Topic) -> Unit)
) :
    ListAdapter<TopicWithQuote, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ItemDictionaryBinding.inflate(
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

    inner class ViewHolder(itemDictionaryBinding: ItemDictionaryBinding) :
        RecyclerView.ViewHolder(itemDictionaryBinding.root) {

        private val binding = itemDictionaryBinding
        private lateinit var adapter: DictionaryQuoteAdapter


        fun bind(data: TopicWithQuote) {
            binding.topic = data.topic

            if (data.topic.isHasChild)
                binding.topicLayout.setOnClickListener { onClickGoNextTopic(data.topic.id) }

            if (data.topic.subTitle.isNotEmpty())
                binding.subTitle.visibility = View.VISIBLE

            if (data.quotes.isNotEmpty()) {
                binding.quotes.visibility = View.VISIBLE
                adapter = DictionaryQuoteAdapter()
                binding.quotes.adapter = adapter
                adapter.submitList(data.quotes)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<TopicWithQuote>() {
        override fun areItemsTheSame(oldItem: TopicWithQuote, newItem: TopicWithQuote): Boolean {
            return oldItem.topic.id == newItem.topic.id
        }

        override fun areContentsTheSame(oldItem: TopicWithQuote, newItem: TopicWithQuote): Boolean {
            return oldItem == newItem
        }
    }

}
