package ru.zfix27r.mylearning.ui.topic

import androidx.recyclerview.widget.RecyclerView
import ru.zfix27r.mylearning.databinding.FragmentTopicEditorAdapterMainBinding

class TopicEditorParentAdapterViewHolder(
    private val binding: FragmentTopicEditorAdapterMainBinding,
    private val callback: TopicEditorParentAdapterCallback,
) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var model: TopicEditorParentUIModel

    init {
        binding.topicEditorAdapterMain.setOnClickListener {
            callback.navigateToSelf(model.id)
        }

        binding.topicEditorAdapterMainDone.setOnClickListener {
            callback.onParentChecked(model)
        }
    }

    fun bind(model: TopicEditorParentUIModel) {
        this.model = model

        model.run {
            binding.topicEditorAdapterMainTitle.text = title
            binding.topicEditorAdapterMainSubtitle.text = subtitle
            binding.topicEditorAdapterMainDone.isChecked = isChecked
        }
    }
}