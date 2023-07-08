package ru.zfix27r.mylearning.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.zfix27r.mylearning.databinding.FragmentMainQuotesItemBinding

class MainAdapter(
    private val callback: () -> MainAdapterCallback,
) : ListAdapter<MainAdapterModel, MainAdapterViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentMainQuotesItemBinding.inflate(inflater, parent, false)
        return MainAdapterViewHolder(binding, callback.invoke())
    }

    override fun onBindViewHolder(holder: MainAdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<MainAdapterModel>() {
        override fun areItemsTheSame(old: MainAdapterModel, new: MainAdapterModel) =
            old.quoteId == new.quoteId

        override fun areContentsTheSame(old: MainAdapterModel, new: MainAdapterModel) =
            old == new
    }
}