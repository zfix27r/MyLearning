package ru.sergeyzabelin.mylearning.ui.content.question

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.zfix27r.data.model.db.Question
import ru.sergeyzabelin.mylearning.databinding.ItemQuestionBinding
import ru.sergeyzabelin.mylearning.ui.content.question.QuestionAdapter.QuestionViewHolder

class QuestionAdapter(private val actionListener: QuestionActionListener) :
    ListAdapter<Question, QuestionViewHolder>(DiffCallback()), View.OnClickListener,
    View.OnLongClickListener {

    override fun getItemCount() = currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemQuestionBinding.inflate(inflater, parent, false)

        return QuestionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val question = currentList[position]
        with(holder.binding) {
            this.question = question
        }
    }

    class QuestionViewHolder(val binding: ItemQuestionBinding) : RecyclerView.ViewHolder(binding.root)

    class DiffCallback : DiffUtil.ItemCallback<Question>() {
        override fun areItemsTheSame(old: Question, new: Question) = old.id == new.id
        override fun areContentsTheSame(old: Question, new: Question) = old == new
    }

    override fun onClick(v: View) {
        val question = v.tag as Question
    }

    override fun onLongClick(v: View): Boolean {
        val question = v.tag as Question

        return false
    }
}
