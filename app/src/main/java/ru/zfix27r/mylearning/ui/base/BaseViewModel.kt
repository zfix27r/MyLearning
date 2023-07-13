package ru.zfix27r.mylearning.ui.base

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.zfix27r.mylearning.ui.activity.RetryCallback

abstract class BaseViewModel : ViewModel(), RetryCallback {
    private var isLoadingDelayTimeout = false

    private val _event = MutableLiveData<BaseViewModelEvent>()
    val event: LiveData<BaseViewModelEvent> = _event

    override fun retry(v: View) {
        _event.postValue(BaseViewModelEvent.Error(null))
    }

    protected fun start() {
        isLoadingDelayTimeout = true
        viewModelScope.launch {
            delay(LOADING_DELAY)
            if (isLoadingDelayTimeout)
                _event.postValue(BaseViewModelEvent.Loading(true))
        }
    }

    protected fun error(messageId: Int) {
        if (isLoadingDelayTimeout)
            isLoadingDelayTimeout = false
        else
            _event.postValue(BaseViewModelEvent.Loading(false))

        _event.postValue(BaseViewModelEvent.Error(messageId))
    }

    protected fun success() {
        if (isLoadingDelayTimeout)
            isLoadingDelayTimeout = false
        else
            _event.postValue(BaseViewModelEvent.Loading(false))

        updateUI()
    }

    protected fun updateUI() {
        _event.postValue(BaseViewModelEvent.UpdateUI(true))
    }

    protected fun finish() {
        _event.postValue(BaseViewModelEvent.Finish(true))
    }

    companion object {
        private const val LOADING_DELAY = 200L
    }
}