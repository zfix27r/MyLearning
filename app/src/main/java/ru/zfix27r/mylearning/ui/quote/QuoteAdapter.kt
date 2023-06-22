package ru.zfix27r.mylearning.ui.quote

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.zfix27r.mylearning.databinding.FragmentQuoteAdapterItemBinding

class QuoteAdapter(
    private val callback: QuoteAdapterCallback,
) : ListAdapter<QuoteUIModel, QuoteAdapterViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentQuoteAdapterItemBinding.inflate(inflater, parent, false)
        return QuoteAdapterViewHolder(binding, callback)
    }

    override fun onBindViewHolder(holder: QuoteAdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<QuoteUIModel>() {
        override fun areItemsTheSame(old: QuoteUIModel, new: QuoteUIModel) = old.id == new.id
        override fun areContentsTheSame(old: QuoteUIModel, new: QuoteUIModel) = old == new
    }
}