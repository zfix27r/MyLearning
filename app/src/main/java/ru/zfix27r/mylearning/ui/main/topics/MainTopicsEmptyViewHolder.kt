package ru.zfix27r.mylearning.ui.main.topics

import androidx.recyclerview.widget.RecyclerView
import ru.zfix27r.mylearning.R
import ru.zfix27r.mylearning.databinding.FragmentMainTopicsItemBinding

class MainTopicsEmptyViewHolder(
    private val binding: FragmentMainTopicsItemBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind() {
        with(binding) {
            mainTopicsItemIcon.setImageResource(R.drawable.ic_ui_notes)
            mainTopicsItemTitle.setText(R.string.topic_empty_name)
        }
    }
}