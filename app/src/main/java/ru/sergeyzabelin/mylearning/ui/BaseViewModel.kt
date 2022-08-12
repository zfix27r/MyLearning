package ru.sergeyzabelin.mylearning.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.zfix27r.domain.model.ResponseModel

abstract class BaseViewModel : ViewModel() {
    protected var isDoneInProgress: MutableLiveData<Boolean> = MutableLiveData(false)
    protected var _result: MutableLiveData<ResponseModel> = MutableLiveData()
    val result: LiveData<ResponseModel> = _result
}