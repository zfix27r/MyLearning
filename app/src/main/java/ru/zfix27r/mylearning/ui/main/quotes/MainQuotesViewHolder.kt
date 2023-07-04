package ru.zfix27r.mylearning.ui.main.quotes

import androidx.recyclerview.widget.RecyclerView
import ru.zfix27r.mylearning.databinding.FragmentMainQuotesItemBinding

class MainQuotesViewHolder(
    private val binding: FragmentMainQuotesItemBinding,
    private val callback: MainQuotesCallback,
) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var quote: MainQuotesModel

    init {
        binding.mainQuotesItem.setOnLongClickListener {
            callback.onLongClick(quote.id)
            true
        }
    }

    fun bind(mainQuotesModel: MainQuotesModel) {
        quote = mainQuotesModel

        binding.mainQuotesItemDescription.text = quote.description
    }
}