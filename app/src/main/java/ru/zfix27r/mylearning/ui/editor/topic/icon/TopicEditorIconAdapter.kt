package ru.zfix27r.mylearning.ui.editor.topic.icon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.zfix27r.mylearning.databinding.FragmentTopicEditorIconAdapterItemBinding

class TopicEditorIconAdapter(
    private val callback: () -> TopicEditorIconAdapterCallback,
) : ListAdapter<Pair<Int, Int>, TopicEditorIconAdapterViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopicEditorIconAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentTopicEditorIconAdapterItemBinding.inflate(inflater, parent, false)
        return TopicEditorIconAdapterViewHolder(binding, callback.invoke())
    }

    override fun onBindViewHolder(holder: TopicEditorIconAdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<Pair<Int, Int>>() {
        override fun areItemsTheSame(oldItem: Pair<Int, Int>, newItem: Pair<Int, Int>) =
            oldItem.first == newItem.first

        override fun areContentsTheSame(oldItem: Pair<Int, Int>, newItem: Pair<Int, Int>) =
            oldItem == newItem
    }
}