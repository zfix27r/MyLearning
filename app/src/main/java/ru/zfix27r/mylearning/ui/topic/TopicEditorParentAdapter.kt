package ru.zfix27r.mylearning.ui.topic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.zfix27r.mylearning.databinding.FragmentTopicEditorAdapterMainBinding

class TopicEditorParentAdapter(
    private val callback: TopicEditorParentAdapterCallback,
) : ListAdapter<TopicEditorParentUIModel, TopicEditorParentAdapterViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopicEditorParentAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentTopicEditorAdapterMainBinding.inflate(inflater, parent, false)
        return TopicEditorParentAdapterViewHolder(binding, callback)
    }

    override fun onBindViewHolder(holder: TopicEditorParentAdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<TopicEditorParentUIModel>() {
        override fun areItemsTheSame(
            oldItem: TopicEditorParentUIModel, newItem: TopicEditorParentUIModel
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: TopicEditorParentUIModel, newItem: TopicEditorParentUIModel
        ) = oldItem == newItem
    }
}