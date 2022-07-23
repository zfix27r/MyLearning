package ru.sergeyzabelin.mylearning.ui.dictionary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.sergeyzabelin.mylearning.data.model.db.Topic
import ru.sergeyzabelin.mylearning.databinding.ItemDictionaryBinding


class DictionaryAdapter(
    private val onClickGoNextTopic: ((Long) -> Unit),
    private val onClickGoTopicContent: ((Long) -> Unit),
    private val onLongClickActionMode: ((Topic) -> Unit)
) :
    ListAdapter<Topic, RecyclerView.ViewHolder>(DiffCallback()) {

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

        fun bind(data: Topic) {
            binding.topic = data

            if (data.isHasChild) setHeaderActive(data)
            else setHeaderInactive()

            setHeaderActionMode(data)

            if (data.counterQuote > 0) setContentActive(data)
            else setContentInactive()
        }

        private fun setHeaderActive(topic: Topic) {
            binding.headerLayout.setOnClickListener { onClickGoNextTopic(topic.id) }
            binding.headerDivider.alpha = 0.4f
        }

        private fun setHeaderInactive() {
            binding.headerDivider.alpha = 0.1f
        }

        private fun setHeaderActionMode(topic: Topic) {
            binding.headerLayout.setOnLongClickListener {
                onLongClickActionMode(topic)
                true
            }
        }

        private fun setContentActive(topic: Topic) {
            binding.contentLayout.setOnClickListener { onClickGoTopicContent(topic.id) }
            binding.quoteIcon.visibility = View.VISIBLE
            binding.quoteCounter.text = topic.counterQuote.toString()
            binding.contentDivider.alpha = 0.2f
        }

        private fun setContentInactive() {
            binding.quoteIcon.visibility = View.GONE
            binding.contentDivider.alpha = 0.1f
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
