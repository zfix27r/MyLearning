package ru.sergeyzabelin.mylearning.ui.content.question

/*class QuestionAdapter(private val actionListener: QuestionActionListener) :
    ListAdapter<QuestionDbEntity, QuestionViewHolder>(DiffCallback()), View.OnClickListener,
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

    class DiffCallback : DiffUtil.ItemCallback<QuestionDbEntity>() {
        override fun areItemsTheSame(old: QuestionDbEntity, new: QuestionDbEntity) = old.id == new.id
        override fun areContentsTheSame(old: QuestionDbEntity, new: QuestionDbEntity) = old == new
    }

    override fun onClick(v: View) {
        val question = v.tag as QuestionDbEntity
    }

    override fun onLongClick(v: View): Boolean {
        val question = v.tag as QuestionDbEntity

        return false
    }
}*/
