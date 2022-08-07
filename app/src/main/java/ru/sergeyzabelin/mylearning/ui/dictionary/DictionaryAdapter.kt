package ru.sergeyzabelin.mylearning.ui.dictionary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.sergeyzabelin.mylearning.R
import ru.zfix27r.data.model.db.Topic
import ru.sergeyzabelin.mylearning.databinding.ItemDictionaryBinding
import ru.sergeyzabelin.mylearning.ui.dictionary.DictionaryAdapter.TopicsViewHolder


class DictionaryAdapter(private val topicActionListener: TopicActionListener) :
    ListAdapter<Topic, TopicsViewHolder>(DiffCallback()),
    View.OnClickListener,
    View.OnLongClickListener {

    override fun getItemCount() = currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDictionaryBinding.inflate(inflater, parent, false)

        binding.headerLayout.setOnClickListener(this)
        binding.headerLayout.setOnLongClickListener(this)
        binding.contentLayout.setOnClickListener(this)

        return TopicsViewHolder(binding)
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

    class DiffCallback : DiffUtil.ItemCallback<Topic>() {
        override fun areItemsTheSame(old: Topic, new: Topic): Boolean = old.id == new.id
        override fun areContentsTheSame(old: Topic, new: Topic): Boolean = old == new
    }

    override fun onClick(v: View) {
        val topic = v.tag as Topic
        when (v.id) {
            R.id.headerLayout -> topicActionListener.onSelf(topic)
            R.id.contentLayout -> topicActionListener.onDetails(topic)
        }
    }

    override fun onLongClick(v: View): Boolean {
        val topic = v.tag as Topic
        when (v.id) {
            R.id.headerLayout -> {
                topicActionListener.onEdit(topic)
                return true
            }
        }
        return false
    }
}
