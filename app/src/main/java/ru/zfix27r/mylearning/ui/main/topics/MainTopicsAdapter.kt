package ru.zfix27r.mylearning.ui.main.topics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.zfix27r.mylearning.databinding.FragmentMainTopicsItemBinding

class MainTopicsAdapter(
    private val callback: () -> MainTopicsCallback,
) : ListAdapter<MainTopicsModel, RecyclerView.ViewHolder>(DiffCallback()) {
    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is MainTopicsModel.Empty -> IS_EMPTY
            else -> IS_TOPIC
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentMainTopicsItemBinding.inflate(inflater, parent, false)

        return when (viewType) {
            IS_EMPTY -> MainTopicsEmptyViewHolder(binding)
            else -> MainTopicsViewHolder(binding, callback.invoke())
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MainTopicsViewHolder -> holder.bind(getItem(position) as MainTopicsModel.Topic)
            is MainTopicsEmptyViewHolder -> holder.bind()
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<MainTopicsModel>() {
        override fun areItemsTheSame(old: MainTopicsModel, new: MainTopicsModel): Boolean =
            old.id == new.id

        override fun areContentsTheSame(old: MainTopicsModel, new: MainTopicsModel): Boolean =
            old == new
    }

    companion object {
        private const val IS_EMPTY = 0
        private const val IS_TOPIC = 1
    }
}