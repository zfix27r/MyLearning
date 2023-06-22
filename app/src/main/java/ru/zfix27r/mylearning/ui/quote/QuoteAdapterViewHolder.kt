package ru.zfix27r.mylearning.ui.quote

import androidx.recyclerview.widget.RecyclerView
import ru.zfix27r.mylearning.databinding.FragmentQuoteAdapterItemBinding

class QuoteAdapterViewHolder(
    private val binding: FragmentQuoteAdapterItemBinding,
    private val callback: QuoteAdapterCallback,
) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var model: QuoteUIModel

    fun bind(model: QuoteUIModel) {
        this.model = model
    }
}