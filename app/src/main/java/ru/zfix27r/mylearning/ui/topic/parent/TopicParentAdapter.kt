package ru.zfix27r.mylearning.ui.topic.parent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.zfix27r.mylearning.databinding.FragmentTopicParentsItemBinding

class TopicParentAdapter(
    private val callback: () -> TopicParentAdapterCallback,
) : ListAdapter<TopicParentAdapterModel, TopicParentAdapterViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): TopicParentAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentTopicParentsItemBinding.inflate(inflater, parent, false)
        return TopicParentAdapterViewHolder(binding, callback.invoke())
    }

    override fun onBindViewHolder(holder: TopicParentAdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<TopicParentAdapterModel>() {
        override fun areItemsTheSame(
            oldItem: TopicParentAdapterModel, newItem: TopicParentAdapterModel
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: TopicParentAdapterModel, newItem: TopicParentAdapterModel
        ) = oldItem == newItem
    }
}