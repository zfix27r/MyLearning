package ru.zfix27r.mylearning.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.zfix27r.mylearning.databinding.FragmentSearchAdapterItemBinding

class SearchAdapter(
    private val callback: SearchAdapterCallback,
) : ListAdapter<SearchAdapterModel, SearchAdapterViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FragmentSearchAdapterItemBinding.inflate(layoutInflater, parent, false)
        return SearchAdapterViewHolder(binding, callback)
    }

    override fun onBindViewHolder(holder: SearchAdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<SearchAdapterModel>() {
        override fun areItemsTheSame(
            oldItem: SearchAdapterModel,
            newItem: SearchAdapterModel
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: SearchAdapterModel,
            newItem: SearchAdapterModel
        ) = oldItem == newItem
    }
}