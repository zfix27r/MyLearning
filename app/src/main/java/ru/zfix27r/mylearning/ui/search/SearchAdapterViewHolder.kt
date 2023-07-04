package ru.zfix27r.mylearning.ui.search

import androidx.recyclerview.widget.RecyclerView
import ru.zfix27r.mylearning.databinding.FragmentSearchAdapterItemBinding

class SearchAdapterViewHolder(
    private val binding: FragmentSearchAdapterItemBinding,
    private val callback: SearchAdapterCallback,
) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var model: SearchAdapterModel

    fun bind(searchAdapterModel: SearchAdapterModel) {
        model = searchAdapterModel

    }
}