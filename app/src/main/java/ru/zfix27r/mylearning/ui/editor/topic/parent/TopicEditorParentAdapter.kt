package ru.zfix27r.mylearning.ui.editor.topic.parent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.zfix27r.mylearning.databinding.FragmentTopicEditorParentAdapterItemBinding

class TopicEditorParentAdapter(
    private val callback: () -> TopicEditorParentAdapterCallback,
) : ListAdapter<TopicEditorParentAdapterModel, TopicEditorParentAdapterViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopicEditorParentAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentTopicEditorParentAdapterItemBinding.inflate(inflater, parent, false)
        return TopicEditorParentAdapterViewHolder(binding, callback.invoke())
    }

    override fun onBindViewHolder(holder: TopicEditorParentAdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<TopicEditorParentAdapterModel>() {
        override fun areItemsTheSame(
            oldItem: TopicEditorParentAdapterModel, newItem: TopicEditorParentAdapterModel
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: TopicEditorParentAdapterModel, newItem: TopicEditorParentAdapterModel
        ) = oldItem == newItem
    }
}