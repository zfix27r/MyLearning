package ru.zfix27r.mylearning.ui.main.quotes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.zfix27r.mylearning.databinding.FragmentMainQuotesItemBinding

class MainQuotesAdapter(
    private val callback: () -> MainQuotesCallback,
) : ListAdapter<MainQuotesModel, MainQuotesViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainQuotesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentMainQuotesItemBinding.inflate(inflater, parent, false)
        return MainQuotesViewHolder(binding, callback.invoke())
    }

    override fun onBindViewHolder(holder: MainQuotesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<MainQuotesModel>() {
        override fun areItemsTheSame(old: MainQuotesModel, new: MainQuotesModel) =
            old.id == new.id

        override fun areContentsTheSame(old: MainQuotesModel, new: MainQuotesModel) =
            old == new
    }
}