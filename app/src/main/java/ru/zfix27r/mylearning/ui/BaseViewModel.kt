package ru.zfix27r.mylearning.ui

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job

abstract class BaseViewModel : ViewModel(), RetryCallback {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isError = MutableLiveData<Int?>()
    val isError: LiveData<Int?> = _isError

    override fun retry(v: View) {
        _isError.postValue(null)
        loading()
    }

    protected fun onStart() {
        _isLoading.postValue(true)
    }

    protected fun onError(stringId: Int) {
        _isError.postValue(stringId)
        _isLoading.postValue(false)
    }

    protected fun onSuccess() {
        _isLoading.postValue(false)
    }

    abstract fun loading(id: Int = 0): Job
}