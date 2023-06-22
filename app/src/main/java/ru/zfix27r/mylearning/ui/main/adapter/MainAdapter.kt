package ru.zfix27r.mylearning.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.zfix27r.mylearning.databinding.FragmentMainAdapterTopicAddBinding
import ru.zfix27r.mylearning.databinding.FragmentMainAdapterTopicBinding
import ru.zfix27r.mylearning.ui.main.MainUIModel

class MainAdapter(
    private val callback: MainAdapterCallback,
) : ListAdapter<MainUIModel, RecyclerView.ViewHolder>(DiffCallback()) {
    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is MainUIModel.Topic -> 0
            is MainUIModel.Add -> 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            1 -> {
                val binding = FragmentMainAdapterTopicAddBinding.inflate(inflater, parent, false)
                MainAdapterTopicAddViewHolder(binding, callback)
            }

            else -> {
                val binding = FragmentMainAdapterTopicBinding.inflate(inflater, parent, false)
                MainAdapterTopicViewHolder(binding, callback)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MainAdapterTopicViewHolder -> holder.bind(getItem(position) as MainUIModel.Topic)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<MainUIModel>() {
        override fun areItemsTheSame(old: MainUIModel, new: MainUIModel): Boolean = old.id == new.id
        override fun areContentsTheSame(old: MainUIModel, new: MainUIModel): Boolean = old == new
    }
}