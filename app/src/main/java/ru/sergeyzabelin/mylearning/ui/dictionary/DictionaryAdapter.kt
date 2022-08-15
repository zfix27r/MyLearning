package ru.sergeyzabelin.mylearning.ui.dictionary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.sergeyzabelin.mylearning.R
import ru.sergeyzabelin.mylearning.databinding.ItemDictionaryBinding
import ru.sergeyzabelin.mylearning.ui.dictionary.DictionaryAdapter.TopicsViewHolder

class DictionaryAdapter(
    private val actionListener: DictionaryActionListener,
    private val contextListener: View.OnCreateContextMenuListener) :
    ListAdapter<TopicSub, TopicsViewHolder>(DiffCallback()),
    View.OnClickListener {

    override fun getItemCount() = currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDictionaryBinding.inflate(inflater, parent, false)

        binding.headerLayout.setOnClickListener(this)
        binding.contentLayout.setOnClickListener(this)
        val holder = TopicsViewHolder(binding)
        binding.headerLayout.setOnCreateContextMenuListener(contextListener)

        return holder
    }

    override fun onBindViewHolder(holder: TopicsViewHolder, position: Int) {
        val topic = currentList[position]
        with(holder.binding) {
            headerLayout.tag = topic
            contentLayout.tag = topic
            this.topic = topic
        }
    }

    class TopicsViewHolder(val binding: ItemDictionaryBinding) :
        RecyclerView.ViewHolder(binding.root)

    class DiffCallback : DiffUtil.ItemCallback<TopicSub>() {
        override fun areItemsTheSame(old: TopicSub, new: TopicSub): Boolean = old.id == new.id
        override fun areContentsTheSame(old: TopicSub, new: TopicSub): Boolean = old == new
    }

    override fun onClick(v: View) {
        val topic = v.tag as TopicSub
        when (v.id) {
            R.id.headerLayout -> actionListener.onSelf(topic.id)
            R.id.contentLayout -> actionListener.onDetails(topic.id)
        }
    }
}
