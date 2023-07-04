package ru.zfix27r.mylearning.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import ru.zfix27r.domain.main.GetMainModel
import ru.zfix27r.domain.main.GetMainQuotesModel
import ru.zfix27r.domain.main.GetMainQuotesUseCase
import ru.zfix27r.domain.main.GetMainTopicsModel
import ru.zfix27r.domain.main.GetMainTopicsUseCase
import ru.zfix27r.domain.main.GetMainUseCase
import ru.zfix27r.mylearning.ui.BaseViewModel
import ru.zfix27r.mylearning.ui.getErrorStringRes
import ru.zfix27r.mylearning.ui.main.quotes.MainQuotesModel
import ru.zfix27r.mylearning.ui.main.topics.MainTopicsModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMain: GetMainUseCase,
    private val getTopics: GetMainTopicsUseCase,
    private val getQuotes: GetMainQuotesUseCase,
) : BaseViewModel() {
    private val _parent = MutableLiveData<MainParentModel>()
    val parent: LiveData<MainParentModel> = _parent

    private val _topics = MutableLiveData<List<MainTopicsModel>>()
    val topics: LiveData<List<MainTopicsModel>> = _topics

    private val _quotes = MutableLiveData<List<MainQuotesModel>>()
    val quotes: LiveData<List<MainQuotesModel>> = _quotes

    init {
        loading(savedStateHandle.get<Int>(TOPIC_ID) ?: 0)
    }

    private fun loading(topicId: Int) = viewModelScope.launch(Dispatchers.IO) {
        combine(
            getMain.execute(topicId),
            getTopics.execute(topicId),
            getQuotes.execute(topicId)
        ) { main, topics, quotes ->
            _parent.postValue(main?.toMainModel())
            _topics.postValue(topics.toListMainTopicsModel())
            _quotes.postValue(quotes.toListMainQuotesModel())
        }.onStart { start() }
            .catch { error(it.getErrorStringRes()) }
            .collectLatest { success() }
    }

    private fun GetMainModel.toMainModel() = MainParentModel(id, title, subtitle)

    private fun List<GetMainTopicsModel>.toListMainTopicsModel() =
        if (this.isEmpty()) listOf(MainTopicsModel.Empty())
        else map { it.run { MainTopicsModel.Topic(id, iconId, title, subtitle) } }

    private fun List<GetMainQuotesModel>.toListMainQuotesModel() =
        map { it.run { MainQuotesModel(id, description) } }

    companion object {
        private const val TOPIC_ID = "topic_id"
    }
}