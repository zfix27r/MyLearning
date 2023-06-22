package ru.zfix27r.mylearning.ui.question

import androidx.recyclerview.widget.RecyclerView
import ru.zfix27r.mylearning.databinding.FragmentQuestionAdapterItemBinding

class QuestionAdapterViewHolder(
    private val binding: FragmentQuestionAdapterItemBinding,
    private val callback: QuestionAdapterCallback,
) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var model: QuestionUIModel

    fun bind(model: QuestionUIModel) {
        this.model = model
    }
}