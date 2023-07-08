package ru.zfix27r.mylearning.ui.main

import androidx.recyclerview.widget.RecyclerView
import ru.zfix27r.mylearning.databinding.FragmentMainQuotesItemBinding
import ru.zfix27r.mylearning.ui.getResIdTopicIcon

class MainAdapterViewHolder(
    private val binding: FragmentMainQuotesItemBinding,
    private val callback: MainAdapterCallback,
) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var main: MainAdapterModel

    init {
        binding.mainItem.setOnClickListener { callback.onClick(main.quoteId) }
    }

    fun bind(mainAdapterModel: MainAdapterModel) {
        main = mainAdapterModel

        binding.mainItemQuoteDescription.text = main.quoteDescription
        binding.mainItemTopicIcon.setImageResource(main.topicIconId.getResIdTopicIcon())
        binding.mainItemTopicTitle.text = main.topicTitle
        binding.mainItemTopicSubtitle.text = main.topicSubtitle
    }
}