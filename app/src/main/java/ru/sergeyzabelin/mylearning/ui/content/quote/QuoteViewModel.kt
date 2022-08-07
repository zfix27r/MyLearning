package ru.sergeyzabelin.mylearning.ui.content.quote

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.zfix27r.data.model.db.Question
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val topicId: Long? = savedStateHandle.get<Long>(TOPIC_ID)
    private val quotes: LiveData<Question> = liveData {}

    init {
        topicId?.let {

        }
    }


    companion object {
        const val TOPIC_ID = "topicId"
    }
}