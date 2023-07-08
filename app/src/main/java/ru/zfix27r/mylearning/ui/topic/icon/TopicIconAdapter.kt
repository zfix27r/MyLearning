package ru.zfix27r.mylearning.ui.topic.icon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.zfix27r.mylearning.databinding.FragmentTopicIconsItemBinding

class TopicIconAdapter(
    private val callback: () -> TopicIconAdapterCallback,
) : ListAdapter<Pair<Int, Int>, TopicIconAdapterViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): TopicIconAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentTopicIconsItemBinding.inflate(inflater, parent, false)
        return TopicIconAdapterViewHolder(binding, callback.invoke())
    }

    override fun onBindViewHolder(holder: TopicIconAdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<Pair<Int, Int>>() {
        override fun areItemsTheSame(oldItem: Pair<Int, Int>, newItem: Pair<Int, Int>) =
            oldItem.first == newItem.first

        override fun areContentsTheSame(oldItem: Pair<Int, Int>, newItem: Pair<Int, Int>) =
            oldItem == newItem
    }
}