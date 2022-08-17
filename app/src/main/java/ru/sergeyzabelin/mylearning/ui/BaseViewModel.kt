package ru.sergeyzabelin.mylearning.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.zfix27r.domain.model.common.ResponseType

abstract class BaseViewModel : ViewModel() {
    protected var isDoneInProgress: MutableLiveData<Boolean> = MutableLiveData(false)
    protected var _response: MutableLiveData<ResponseType> = MutableLiveData()
    val response: LiveData<ResponseType> = _response
}