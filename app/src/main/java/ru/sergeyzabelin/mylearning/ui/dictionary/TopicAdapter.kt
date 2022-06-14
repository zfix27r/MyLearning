package ru.sergeyzabelin.mylearning.ui.dictionary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.sergeyzabelin.mylearning.data.model.db.Topic
import ru.sergeyzabelin.mylearning.databinding.ItemDictionaryTopicViewBinding

class TopicAdapter(
    private val onClickToDetail: ((Long) -> Unit)
) :
    ListAdapter<Topic, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ItemDictionaryTopicViewBinding.inflate(
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

    inner class ViewHolder(itemDictionaryTopicViewBinding: ItemDictionaryTopicViewBinding) :
        RecyclerView.ViewHolder(itemDictionaryTopicViewBinding.root) {

        private val binding = itemDictionaryTopicViewBinding

        fun bind(topic: Topic) {
            binding.topic = topic
            binding.dictionaryTopicItem.setOnClickListener { onClickToDetail(topic.id) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Topic>() {
        override fun areItemsTheSame(oldItem: Topic, newItem: Topic): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Topic, newItem: Topic): Boolean {
            return oldItem == newItem
        }
    }
}
