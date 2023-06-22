package ru.zfix27r.mylearning.ui.main.adapter

import androidx.recyclerview.widget.RecyclerView
import ru.zfix27r.mylearning.databinding.FragmentMainAdapterTopicAddBinding

class MainAdapterTopicAddViewHolder(
    binding: FragmentMainAdapterTopicAddBinding,
    private val callback: MainAdapterCallback,
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.mainAdapterTopicAdd.setOnClickListener { callback.navigateToTopicAdd() }
    }
}