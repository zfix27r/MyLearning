package ru.sergeyzabelin.mylearning.ui.dictionary.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.sergeyzabelin.mylearning.data.model.db.Dictionary
import ru.sergeyzabelin.mylearning.databinding.ItemDictionaryViewBinding
import ru.sergeyzabelin.mylearning.ui.dictionary.DictionaryAdapterDiffCallback

class SourceAdapter(
    private val onClickRecyclerItem: ((Int) -> Unit)
) :
    ListAdapter<Dictionary, RecyclerView.ViewHolder>(DictionaryAdapterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ItemDictionaryViewBinding.inflate(
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

    inner class ViewHolder(itemDictionaryViewBinding: ItemDictionaryViewBinding) :
        RecyclerView.ViewHolder(itemDictionaryViewBinding.root) {

        private val binding = itemDictionaryViewBinding

        fun bind(dictionary: Dictionary) {
            binding.dictionary = dictionary
            binding.dictionaryAddItem.setOnClickListener { onClickRecyclerItem(dictionary.id) }
        }
    }
}
