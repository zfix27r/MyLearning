package ru.sergeyzabelin.mylearning.ui.dictionary

import androidx.recyclerview.widget.DiffUtil
import ru.sergeyzabelin.mylearning.data.model.db.Dictionary

class DictionaryAdapterDiffCallback : DiffUtil.ItemCallback<Dictionary>() {
    override fun areItemsTheSame(oldItem: Dictionary, newItem: Dictionary): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Dictionary, newItem: Dictionary): Boolean {
        return oldItem == newItem
    }
}