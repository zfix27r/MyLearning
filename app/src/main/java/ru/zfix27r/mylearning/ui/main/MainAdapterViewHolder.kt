package ru.zfix27r.mylearning.ui.main

import androidx.recyclerview.widget.RecyclerView
import ru.zfix27r.mylearning.databinding.FragmentMainAdapterItemBinding
import ru.zfix27r.mylearning.ui.getResIdTopicIcon
import ru.zfix27r.mylearning.ui.setTextOrGone
import ru.zfix27r.mylearning.ui.setTextOrUndefined

class MainAdapterViewHolder(
    private val binding: FragmentMainAdapterItemBinding,
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
        binding.mainItemTopicTitle.setTextOrUndefined(main.topicTitle)
        binding.mainItemTopicSubtitle.setTextOrGone(main.topicSubtitle)
    }
}