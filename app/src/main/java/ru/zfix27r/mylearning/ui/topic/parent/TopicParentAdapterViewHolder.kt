package ru.zfix27r.mylearning.ui.topic.parent

import android.util.TypedValue
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import ru.zfix27r.mylearning.databinding.FragmentTopicParentsItemBinding

class TopicParentAdapterViewHolder(
    private val binding: FragmentTopicParentsItemBinding,
    private val callback: TopicParentAdapterCallback,
) : RecyclerView.ViewHolder(binding.root) {
    private var _topic: TopicParentAdapterModel? = null
    private val topic
        get() = _topic!!

    private val colorSelected by lazy {
        val value = TypedValue()
        binding.root.context.theme.resolveAttribute(
            com.google.android.material.R.attr.colorPrimaryInverse,
            value,
            true
        )
        value.data
    }

    private val colorUnselected by lazy {
        val value = TypedValue()
        binding.root.context.theme.resolveAttribute(
            com.google.android.material.R.attr.colorControlNormal,
            value,
            true
        )
        value.data
    }

    init {
        binding.topicParentsItem.setOnClickListener {
            _topic?.let { callback.onClick(it) }
        }

        binding.topicParentsItemEdit.setOnClickListener {
            _topic?.let { callback.onLongClick(it.id) }
        }

        binding.topicParentsItemForward.setOnClickListener {
            _topic?.let { callback.onClickForward(it.id) }
        }
    }

    fun bind(topicEditorParentAdapterModel: TopicParentAdapterModel) {
        if (topicEditorParentAdapterModel.id != _topic?.id) {
            _topic = topicEditorParentAdapterModel

            binding.disableEditableItem()
            binding.setItemBackgroundColor()
            binding.topicParentsItemSubtitle.isVisible = topic.subtitle.isNotEmpty()

            binding.topicParentsItemTitle.text = topic.title
            binding.topicParentsItemSubtitle.text = topic.subtitle
            binding.topicParentsItemForward.text = topic.childCount.toString()
        }

        if (topic.isShowEditUI) showEditUI() else hideEditUI()
    }

    private fun FragmentTopicParentsItemBinding.disableEditableItem() {
        topicParentsItem.isEnabled = !topic.isEditable
    }

    private fun FragmentTopicParentsItemBinding.setItemBackgroundColor() {
        topicParentsItem.setBackgroundColor(
            if (topic.isSelect) colorSelected else colorUnselected
        )
    }

    private fun showEditUI() {
        binding.topicParentsItemForward.isVisible = false
        binding.topicParentsItemEdit.isVisible = true
    }

    private fun hideEditUI() {
        binding.topicParentsItemEdit.isVisible = false
        binding.topicParentsItemForward.isVisible = topic.childCount > 0
    }
}