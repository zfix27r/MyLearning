package ru.sergeyzabelin.mylearning.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.sergeyzabelin.mylearning.data.entities.Dictionary
import ru.sergeyzabelin.mylearning.databinding.RecyclerDictionaryItemBinding

class DictionaryAdapter(
    private val onSelectedListener: ((Int) -> Unit)
) :
    ListAdapter<Dictionary, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            RecyclerDictionaryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ViewHolder
        viewHolder.bind(currentList[position])
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    inner class ViewHolder(recyclerDictionaryItemBinding: RecyclerDictionaryItemBinding) :
        RecyclerView.ViewHolder(recyclerDictionaryItemBinding.root) {

        private val binding = recyclerDictionaryItemBinding

        fun bind(dictionary: Dictionary) {
            binding.dictionary = dictionary
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Dictionary>() {
        override fun areItemsTheSame(oldItem: Dictionary, newItem: Dictionary): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Dictionary, newItem: Dictionary): Boolean {
            return oldItem == newItem
        }
    }
}
