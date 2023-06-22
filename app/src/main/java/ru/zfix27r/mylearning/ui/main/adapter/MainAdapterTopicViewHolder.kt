package ru.zfix27r.mylearning.ui.main.adapter

import androidx.recyclerview.widget.RecyclerView
import ru.zfix27r.mylearning.databinding.FragmentMainAdapterTopicBinding
import ru.zfix27r.mylearning.ui.main.MainUIModel

class MainAdapterTopicViewHolder(
    private val binding: FragmentMainAdapterTopicBinding,
    private val callback: MainAdapterCallback,
) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var model: MainUIModel

    init {
        binding.mainAdapterTopic.setOnClickListener { callback.navigateToSelf(model.id) }
        binding.mainAdapterTopicMore.setOnClickListener { callback.viewAdapterItemMenu(model.id) }
    }

    fun bind(model: MainUIModel.Topic) {
        this.model = model

        with(binding) {
            mainAdapterTopicTitle.text = model.title
            mainAdapterTopicSubtitle.text = model.subtitle
        }
    }
}