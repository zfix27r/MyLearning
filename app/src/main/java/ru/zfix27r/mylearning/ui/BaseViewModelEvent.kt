package ru.zfix27r.mylearning.ui

sealed class BaseViewModelEvent {
    data class Loading(val state: Boolean) : BaseViewModelEvent()
    data class Error(val messageId: Int?) : BaseViewModelEvent()
    data class UpdateUI(val state: Boolean): BaseViewModelEvent()
    data class Finish(val state: Boolean) : BaseViewModelEvent()
}