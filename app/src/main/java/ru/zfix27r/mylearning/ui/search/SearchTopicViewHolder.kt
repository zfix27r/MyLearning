package ru.zfix27r.mylearning.ui.search

import androidx.recyclerview.widget.RecyclerView
import ru.zfix27r.domain.activity.SearchModel
import ru.zfix27r.mylearning.databinding.ActivitySearchAdapterTopicBinding
import ru.zfix27r.mylearning.ui.getResIdTopicIcon
import ru.zfix27r.mylearning.ui.setTextOrGone
import ru.zfix27r.mylearning.ui.setTextOrUndefined

class SearchTopicViewHolder(
    private val binding: ActivitySearchAdapterTopicBinding,
    private val callback: SearchAdapterCallback,
) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var topic: SearchModel.Topic

    fun bind(searchModelTopic: SearchModel.Topic) {
        topic = searchModelTopic

        binding.searchTopicIcon.setImageResource(topic.iconId.getResIdTopicIcon())
        binding.searchTopicTitle.setTextOrUndefined(topic.title)
        binding.searchTopicSubtitle.setTextOrGone(topic.subtitle)
    }
}