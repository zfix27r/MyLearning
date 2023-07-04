package ru.zfix27r.mylearning.ui.editor.quote

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
import ru.zfix27r.mylearning.ui.editor.EMPTY
import ru.zfix27r.mylearning.ui.editor.OK
import ru.zfix27r.mylearning.ui.getErrorStringRes
import ru.zfix27r.mylearning.ui.getMoreThanZeroOrNull
import javax.inject.Inject

@HiltViewModel
class QuoteEditorViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getQuote: GetQuoteEditorModelUseCase,
    private val saveQuote: SaveQuoteEditorModelUseCase,
) : BaseViewModel() {
    private val _quote = QuoteEditorSaveModel(
        savedStateHandle.get<Int>(QUOTE_ID).getMoreThanZeroOrNull()
    )
    private val quoteId
        get() = _quote.id
    var quoteDescription
        get() = _quote.description
        set(value) {
            _quote.statusDescription = if (value.isNotEmpty()) OK else EMPTY
            _quote.description = value
        }
    var topicId
        get() = _quote.topicId
        set(value) {
            _quote.topicId = value
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
    val statusDescription
        get() = _quote.statusDescription

    init {
        loading()
    }

    private fun loading() {
        quoteId?.let { id ->
            if (isBlockInput) return
            isBlockInput = true

            viewModelScope.launch(Dispatchers.IO) {
                getQuote.execute(id)
                    .onStart { start() }
                    .catch {
                        error(it.getErrorStringRes())
                        isBlockInput = false
                    }
                    .collectLatest {
                        _quote.description = it.description
                        _quote.statusDescription = OK

                        _quote.topicId = it.topicId ?: 0
                        _quote.topicTitle = it.topicTitle ?: ""
                        _quote.topicSubtitle = it.topicSubtitle ?: ""
                        success()
                    }
            }
        }
    }

    fun trySave() {
        if (_quote.statusDescription == OK) {
            if (isBlockInput) return
            isBlockInput = true

            viewModelScope.launch(Dispatchers.IO) {
                saveQuote.execute(_quote.toSaveQuoteEditorModel())
                    .onStart { start() }
                    .catch {
                        error(it.getErrorStringRes())
                    }
                    .collectLatest {
                        finish()
                    }
            }
        }
    }

    private fun QuoteEditorSaveModel.toSaveQuoteEditorModel() =
        SaveQuoteEditorModel(
            id = id,
            topicId = topicId.getMoreThanZeroOrNull(),
            description = description
        )

    companion object {
        private const val QUOTE_ID = "quote_id"
    }
}