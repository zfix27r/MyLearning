package ru.zfix27r.mylearning.ui.editor.topic.icon

import androidx.recyclerview.widget.RecyclerView
import ru.zfix27r.mylearning.databinding.FragmentTopicEditorIconAdapterItemBinding

class TopicEditorIconAdapterViewHolder(
    private val binding: FragmentTopicEditorIconAdapterItemBinding,
    private val callback: TopicEditorIconAdapterCallback,
) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var icon: Pair<Int, Int>

    init {
        binding.topicEditorIconAdapterItemIcon.setOnClickListener {
            callback.onClick(icon.first)
        }
    }

    fun bind(iconPair: Pair<Int, Int>) {
        icon = iconPair

        binding.topicEditorIconAdapterItemIcon.setImageResource(icon.second)
    }
}