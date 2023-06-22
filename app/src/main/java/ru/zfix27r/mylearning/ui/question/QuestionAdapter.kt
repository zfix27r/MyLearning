package ru.zfix27r.mylearning.ui.question

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.zfix27r.mylearning.databinding.FragmentQuestionAdapterItemBinding

class QuestionAdapter(
    private val callback: QuestionAdapterCallback,
) : ListAdapter<QuestionUIModel, QuestionAdapterViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentQuestionAdapterItemBinding.inflate(inflater, parent, false)
        return QuestionAdapterViewHolder(binding, callback)
    }

    override fun onBindViewHolder(holder: QuestionAdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<QuestionUIModel>() {
        override fun areItemsTheSame(old: QuestionUIModel, new: QuestionUIModel) = old.id == new.id
        override fun areContentsTheSame(old: QuestionUIModel, new: QuestionUIModel) = old == new
    }
}
