package ru.zfix27r.mylearning.ui.main.topics

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import ru.zfix27r.mylearning.databinding.FragmentMainTopicsItemBinding
import ru.zfix27r.mylearning.ui.getResIdTopicIcon

class MainTopicsViewHolder(
    private val binding: FragmentMainTopicsItemBinding,
    private val callback: MainTopicsCallback,
) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var topic: MainTopicsModel.Topic

    init {
        binding.mainTopicsItem.setOnClickListener { callback.onClick(topic.id) }
        binding.mainTopicsItem.setOnLongClickListener {
            callback.onLongClick(topic.id)
            true
        }
    }

    fun bind(mainTopicsModel: MainTopicsModel.Topic) {
        this.topic = mainTopicsModel

        with(binding) {
            mainTopicsItemIcon.setImageResource(topic.icon.getResIdTopicIcon())
            mainTopicsItemTitle.text = topic.title

            topic.subtitle?.let {
                mainTopicsItemSubtitle.isVisible = true
                mainTopicsItemSubtitle.text = it
            } ?: run {
                mainTopicsItemSubtitle.isVisible = false
            }
        }
    }
}