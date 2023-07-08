package ru.zfix27r.mylearning.ui.topic.icon

import androidx.recyclerview.widget.RecyclerView
import ru.zfix27r.mylearning.databinding.FragmentTopicIconsItemBinding

class TopicIconAdapterViewHolder(
    private val binding: FragmentTopicIconsItemBinding,
    private val callback: TopicIconAdapterCallback,
) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var icon: Pair<Int, Int>

    init {
        binding.topicIconsItem.setOnClickListener {
            callback.onClick(icon.first)
        }
    }

    fun bind(iconPair: Pair<Int, Int>) {
        icon = iconPair

        binding.topicIconsItem.setImageResource(icon.second)
    }
}