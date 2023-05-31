package ru.sergeyzabelin.zfix27r.ui.dictionary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.sergeyzabelin.zfix27r.R
import ru.sergeyzabelin.zfix27r.databinding.ItemDictionaryBinding
import ru.sergeyzabelin.zfix27r.ui.dictionary.DictionaryAdapter.TopicsViewHolder

class DictionaryAdapter(
    private val actionListener: DictionaryActionListener,
    private val contextListener: View.OnCreateContextMenuListener
) :
    ListAdapter<Topics, TopicsViewHolder>(DiffCallback()),
    View.OnClickListener {

    override fun getItemCount() = currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDictionaryBinding.inflate(inflater, parent, false)

        binding.headerLayout.setOnClickListener(this)
        binding.contentLayout.setOnClickListener(this)
        binding.headerLayout.setOnCreateContextMenuListener(contextListener)

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

    class DiffCallback : DiffUtil.ItemCallback<Topics>() {
        override fun areItemsTheSame(old: Topics, new: Topics): Boolean = old.id == new.id
        override fun areContentsTheSame(old: Topics, new: Topics): Boolean = old == new
    }

    override fun onClick(v: View) {
        val topic = v.tag as Topics
        when (v.id) {
            R.id.headerLayout -> actionListener.onSelf(topic.id)
            R.id.contentLayout -> actionListener.onDetails(topic.id)
        }
    }
}
