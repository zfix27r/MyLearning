package ru.zfix27r.mylearning.ui.quote

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import ru.zfix27r.domain.editor.quote.GetQuoteEditorModelUseCase
import ru.zfix27r.domain.editor.quote.SaveQuoteEditorModel
import ru.zfix27r.domain.editor.quote.SaveQuoteEditorModelUseCase
import ru.zfix27r.mylearning.ui.BaseViewModel
import ru.zfix27r.mylearning.ui.getErrorStringRes
import ru.zfix27r.mylearning.ui.getMoreThanZeroOrNull
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getQuote: GetQuoteEditorModelUseCase,
    private val saveQuote: SaveQuoteEditorModelUseCase,
) : BaseViewModel() {
    private val _quote = QuoteSaveModel(
        savedStateHandle.get<Int>(QUOTE_ID).getMoreThanZeroOrNull()
    )
    private val quoteId
        get() = _quote.id
    var quoteDescription
        get() = _quote.description
        set(value) {
            _quote.description = value
        }
    var topicId
        get() = _quote.topicId
        set(value) {
            _quote.topicId = value
        }
    var topicIconId
        get() = _quote.topicIconId
        set(value) {
            _quote.topicIconId = value
        }
    var topicTitle
        get() = _quote.topicTitle
        set(value) {
            _quote.topicTitle = value
        }
    var topicSubtitle
        get() = _quote.topicSubtitle
        set(value) {
            _quote.topicSubtitle = value
        }

    init {
        loading()
    }

    private fun loading() {
        quoteId?.let { id ->
            viewModelScope.launch(Dispatchers.IO) {
                getQuote.execute(id)
                    .onStart { start() }
                    .catch { error(it.getErrorStringRes()) }
                    .collectLatest {
                        _quote.description = it.description
                        _quote.topicId = it.topicId ?: 0
                        _quote.topicIconId = it.topicIconId ?: 0
                        _quote.topicTitle = it.topicTitle ?: ""
                        _quote.topicSubtitle = it.topicSubtitle ?: ""
                        success()
                    }
            }
        }
    }

    fun save(description: String) {
        _quote.description = description

        viewModelScope.launch(Dispatchers.IO) {
            saveQuote.execute(_quote.toSaveQuoteEditorModel())
                .onStart { start() }
                .catch { error(it.getErrorStringRes()) }
                .collectLatest { }
        }
    }

    private fun QuoteSaveModel.toSaveQuoteEditorModel() =
        SaveQuoteEditorModel(
            id = id,
            topicId = topicId.getMoreThanZeroOrNull(),
            description = description.trim()
        )

    companion object {
        private const val QUOTE_ID = "quote_id"
    }
}