package ru.zfix27r.mylearning.ui.topic.icon

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.zfix27r.mylearning.ui.base.BaseViewModel
import ru.zfix27r.mylearning.ui.topicIcons
import javax.inject.Inject

@HiltViewModel
class TopicIconViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {
    private val selectedIcon = savedStateHandle.get<Int>(TOPIC_ICON_ID) ?: 0

    val icons = topicIcons.filter { it.first != selectedIcon }

    companion object {
        private const val TOPIC_ICON_ID = "topic_icon_id"
    }
}