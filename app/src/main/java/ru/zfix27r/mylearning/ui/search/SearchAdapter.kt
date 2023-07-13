package ru.zfix27r.mylearning.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.zfix27r.domain.activity.SearchModel
import ru.zfix27r.mylearning.databinding.ActivitySearchAdapterEmptyBinding
import ru.zfix27r.mylearning.databinding.ActivitySearchAdapterHeaderBinding
import ru.zfix27r.mylearning.databinding.ActivitySearchAdapterQuoteBinding
import ru.zfix27r.mylearning.databinding.ActivitySearchAdapterSourceBinding
import ru.zfix27r.mylearning.databinding.ActivitySearchAdapterTopicBinding

class SearchAdapter(
    private val callback: SearchAdapterCallback,
) : ListAdapter<SearchModel, RecyclerView.ViewHolder>(DiffCallback()) {
    private var hasHeaderTopic = false
    private var hasHeaderQuote = false
    private var hasHeaderSource = false
    override fun getItemCount() =
        if (currentList.isEmpty()) 1
        else {
            var size = super.getItemCount()
            if (hasHeaderTopic) size++
            if (hasHeaderQuote) size++
            if (hasHeaderSource) size++
            size
        }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is SearchModel.Topic -> if (!hasHeaderTopic) TOPIC_HEADER else TOPIC
            is SearchModel.Quote -> if (!hasHeaderQuote) QUOTE_HEADER else QUOTE
            is SearchModel.Source -> if (!hasHeaderSource) SOURCE_HEADER else SOURCE
            else -> EMPTY
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TOPIC_HEADER, QUOTE_HEADER, SOURCE_HEADER -> getHeaderViewHolder(parent)
            TOPIC -> getTopicViewHolder(parent)
            QUOTE -> getQuoteViewHolder(parent)
            SOURCE -> getSourceViewHolder(parent)
            else -> getEmptyViewHolder(parent)
        }
    }

    private fun getHeaderViewHolder(parent: ViewGroup): SearchHeaderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ActivitySearchAdapterHeaderBinding.inflate(inflater, parent, false)
        return SearchHeaderViewHolder(binding)
    }

    private fun getEmptyViewHolder(parent: ViewGroup): SearchEmptyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ActivitySearchAdapterEmptyBinding.inflate(inflater, parent, false)
        return SearchEmptyViewHolder(binding)
    }

    private fun getTopicViewHolder(parent: ViewGroup): SearchTopicViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ActivitySearchAdapterTopicBinding.inflate(inflater, parent, false)
        return SearchTopicViewHolder(binding, callback)
    }

    private fun getQuoteViewHolder(parent: ViewGroup): SearchQuoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ActivitySearchAdapterQuoteBinding.inflate(inflater, parent, false)
        return SearchQuoteViewHolder(binding, callback)
    }

    private fun getSourceViewHolder(parent: ViewGroup): SearchSourceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ActivitySearchAdapterSourceBinding.inflate(inflater, parent, false)
        return SearchSourceViewHolder(binding, callback)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SearchHeaderViewHolder -> {}
            is SearchTopicViewHolder -> holder.bind(getItem(position) as SearchModel.Topic)
            is SearchQuoteViewHolder -> holder
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<SearchModel>() {
        override fun areItemsTheSame(
            oldItem: SearchModel,
            newItem: SearchModel
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: SearchModel,
            newItem: SearchModel
        ) = oldItem == newItem
    }

    companion object {
        private const val EMPTY = 1
        private const val TOPIC = 2
        private const val TOPIC_HEADER = 3
        private const val QUOTE = 4
        private const val QUOTE_HEADER = 5
        private const val SOURCE = 6
        private const val SOURCE_HEADER = 7
    }
}