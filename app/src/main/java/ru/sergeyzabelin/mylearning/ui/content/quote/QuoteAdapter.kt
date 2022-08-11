package ru.sergeyzabelin.mylearning.ui.content.quote

/*

class QuoteAdapter(private val actionListener: QuoteActionListener) :
    ListAdapter<ContentQuoteWithSource, QuoteViewHolder>(DiffCallback()), View.OnClickListener,
    View.OnLongClickListener {

    override fun getItemCount() = currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemQuoteBinding.inflate(inflater, parent, false)

        binding.item.setOnLongClickListener(this)
        binding.sourceLayout.setOnClickListener(this)
        binding.sourceLayout.setOnLongClickListener(this)

        return QuoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val quoteWithSource = currentList[position]
        with(holder.binding) {
            item.tag = quoteWithSource
            sourceLayout.tag = quoteWithSource
            this.quote = quoteWithSource.quote
            this.source = quoteWithSource.source
        }
    }

    class QuoteViewHolder(val binding: ItemQuoteBinding) : RecyclerView.ViewHolder(binding.root)

    class DiffCallback : DiffUtil.ItemCallback<ContentQuoteWithSource>() {
        override fun areItemsTheSame(old: ContentQuoteWithSource, new: ContentQuoteWithSource) =
            old.quote.id == new.quote.id

        override fun areContentsTheSame(old: ContentQuoteWithSource, new: ContentQuoteWithSource) =
            old.quote == new.quote
    }

    override fun onClick(v: View) {
        val quoteWithSource = v.tag as ContentQuoteWithSource
        when(v.id) {
            R.id.sourceLayout -> actionListener.onUrlOpen(quoteWithSource.source)
        }
    }

    override fun onLongClick(v: View): Boolean {
        val quoteWithSource = v.tag as ContentQuoteWithSource
        when(v.id) {
            R.id.item -> actionListener.onQuoteEdit(quoteWithSource.quote)
            R.id.sourceLayout -> actionListener.onSourceEdit(quoteWithSource.source)
        }

        return false
    }
}
*/
