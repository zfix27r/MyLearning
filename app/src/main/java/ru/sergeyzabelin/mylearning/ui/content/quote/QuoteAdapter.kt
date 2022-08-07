package ru.sergeyzabelin.mylearning.ui.content.quote

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.sergeyzabelin.mylearning.R
import ru.zfix27r.data.model.db.QuoteWithSource
import ru.sergeyzabelin.mylearning.databinding.ItemQuoteBinding
import ru.sergeyzabelin.mylearning.ui.content.quote.QuoteAdapter.QuoteViewHolder

class QuoteAdapter(private val actionListener: QuoteActionListener) :
    ListAdapter<QuoteWithSource, QuoteViewHolder>(DiffCallback()), View.OnClickListener,
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

    class DiffCallback : DiffUtil.ItemCallback<QuoteWithSource>() {
        override fun areItemsTheSame(old: QuoteWithSource, new: QuoteWithSource) =
            old.quote.id == new.quote.id

        override fun areContentsTheSame(old: QuoteWithSource, new: QuoteWithSource) =
            old.quote == new.quote
    }

    override fun onClick(v: View) {
        val quoteWithSource = v.tag as QuoteWithSource
        when(v.id) {
            R.id.sourceLayout -> actionListener.onUrlOpen(quoteWithSource.source)
        }
    }

    override fun onLongClick(v: View): Boolean {
        val quoteWithSource = v.tag as QuoteWithSource
        when(v.id) {
            R.id.item -> actionListener.onQuoteEdit(quoteWithSource.quote)
            R.id.sourceLayout -> actionListener.onSourceEdit(quoteWithSource.source)
        }

        return false
    }
}
