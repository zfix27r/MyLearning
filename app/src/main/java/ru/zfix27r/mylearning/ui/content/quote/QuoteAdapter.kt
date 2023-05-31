package ru.sergeyzabelin.zfix27r.ui.content.quote

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.sergeyzabelin.zfix27r.R
import ru.sergeyzabelin.zfix27r.databinding.ItemQuoteBinding
import ru.sergeyzabelin.zfix27r.ui.content.quote.QuoteAdapter.QuotesViewHolder

class QuoteAdapter(
    private val actionListener: QuoteActionListener,
    private val contextListener: View.OnCreateContextMenuListener
) :
    ListAdapter<QuoteWithSource, QuotesViewHolder>(DiffCallback()),
    View.OnClickListener {

    override fun getItemCount() = currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemQuoteBinding.inflate(inflater, parent, false)

        binding.sourceLayout.setOnClickListener(this)
        binding.item.setOnCreateContextMenuListener(contextListener)

        return QuotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        val quoteWithSource = currentList[position]
        with(holder.binding) {
            sourceLayout.tag = quoteWithSource.source
            this.quote = quoteWithSource.quote
            this.source = quoteWithSource.source
        }
    }

    class QuotesViewHolder(val binding: ItemQuoteBinding) : RecyclerView.ViewHolder(binding.root)

    class DiffCallback : DiffUtil.ItemCallback<QuoteWithSource>() {
        override fun areItemsTheSame(old: QuoteWithSource, new: QuoteWithSource) =
            old.quote.id == new.quote.id

        override fun areContentsTheSame(old: QuoteWithSource, new: QuoteWithSource) =
            old.quote == new.quote
    }

    override fun onClick(v: View) {
        val source = v.tag as Source
        when (v.id) {
            R.id.sourceLayout -> actionListener.onUrlOpen(source)
        }
    }
}