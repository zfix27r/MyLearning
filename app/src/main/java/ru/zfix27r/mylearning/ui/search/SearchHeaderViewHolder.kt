package ru.zfix27r.mylearning.ui.search

import androidx.recyclerview.widget.RecyclerView
import ru.zfix27r.mylearning.databinding.ActivitySearchAdapterHeaderBinding

class SearchHeaderViewHolder(
    private val binding: ActivitySearchAdapterHeaderBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(headerRes: Int) {
        binding.searchAdapterHeader.setText(headerRes)
    }
}