package ru.zfix27r.mylearning.ui.main.adapter

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.zfix27r.mylearning.ui.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class MainAdapterItemModalViewModel @Inject constructor(
) : BaseViewModel() {
    private var _topicId: Int = 0

    val topicId
        get() = _topicId

    override fun loading(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        _topicId = id
    }
}