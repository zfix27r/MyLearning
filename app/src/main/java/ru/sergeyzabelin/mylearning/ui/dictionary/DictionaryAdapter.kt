package ru.sergeyzabelin.mylearning.ui.dictionary

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.sergeyzabelin.mylearning.data.model.db.Dictionary
import ru.sergeyzabelin.mylearning.databinding.ItemDictionaryBinding


class DictionaryAdapter(
    private val onClickGoNext: ((Long) -> Unit),
    private val onLongClickActionMode: ((Long) -> Unit)
) :
    ListAdapter<Dictionary, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ItemDictionaryBinding.inflate(
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

    inner class ViewHolder(itemDictionaryBinding: ItemDictionaryBinding) :
        RecyclerView.ViewHolder(itemDictionaryBinding.root) {

        private val binding = itemDictionaryBinding
        private lateinit var adapter: DictionaryArticleAdapter


        fun bind(dictionary: Dictionary) {
            binding.topic = dictionary.topic
            binding.dictionaryItem.setOnClickListener { onClickGoNext(dictionary.topic.id) }
            binding.dictionaryItem.setOnLongClickListener {
                onLongClickActionMode(dictionary.topic.id)
                true
            }


            binding.dictionaryArticlesRecycler.visibility = View.VISIBLE
            adapter = DictionaryArticleAdapter { id -> onClick(id) }
            binding.dictionaryArticlesRecycler.adapter = adapter
            adapter.submitList(dictionary.articles)
        }

        fun onClick(url: String) {
            Log.e("onClick", url)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Dictionary>() {
        override fun areItemsTheSame(oldItem: Dictionary, newItem: Dictionary): Boolean {
            return oldItem.topic.id == newItem.topic.id
        }

        override fun areContentsTheSame(oldItem: Dictionary, newItem: Dictionary): Boolean {
            return oldItem == newItem
        }
    }

}
