package ru.zfix27r.mylearning.ui.editor.topic.parent

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import ru.zfix27r.mylearning.databinding.FragmentTopicEditorParentAdapterItemBinding

class TopicEditorParentAdapterViewHolder(
    private val binding: FragmentTopicEditorParentAdapterItemBinding,
    private val callback: TopicEditorParentAdapterCallback,
) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var topic: TopicEditorParentAdapterModel

    init {
        binding.topicEditorParentItem.setOnClickListener {
            binding.topicEditorParentItemSelect.isVisible = !topic.isSelect
            callback.onClick(topic)
        }

        binding.topicEditorParentItemNext.setOnClickListener {
            callback.onClickNext(topic.id)
        }
    }

    fun bind(topicEditorParentAdapterModel: TopicEditorParentAdapterModel) {
        this.topic = topicEditorParentAdapterModel

        binding.topicEditorParentItem.isEnabled = !topic.isEditable
        binding.topicEditorParentItemSelect.isVisible = topic.isSelect
        binding.topicEditorParentItemSubtitle.isVisible = topic.subtitle.isNotEmpty()
        binding.topicEditorParentItemNext.isVisible = topic.childCount > 0

        binding.topicEditorParentItemTitle.text = topic.title
        binding.topicEditorParentItemSubtitle.text = topic.subtitle
        binding.topicEditorParentItemNext.text = topic.childCount.toString()
    }
}