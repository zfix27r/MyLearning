package ru.zfix27r.mylearning.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import ru.zfix27r.domain.main.GetMainModel
import ru.zfix27r.domain.main.GetMainModelsUseCase
import ru.zfix27r.mylearning.ui.base.BaseViewModel
import ru.zfix27r.mylearning.ui.getErrorStringRes
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMain: GetMainModelsUseCase,
) : BaseViewModel() {
    private val _mains = MutableLiveData<List<MainAdapterModel>>()
    val mains: LiveData<List<MainAdapterModel>> = _mains

    init {
        loading()
    }

    private fun loading() = viewModelScope.launch(Dispatchers.IO) {
        getMain.execute()
            .onStart { start() }
            .catch { error(it.getErrorStringRes()) }
            .collectLatest {
                _mains.postValue(it.toListMainAdapterModel())
                success()
            }
    }
    private fun List<GetMainModel>.toListMainAdapterModel() =
        map {
            it.run {
                MainAdapterModel(
                    quoteId,
                    quoteDescription,
                    topicId,
                    topicIconId,
                    topicTitle,
                    topicSubtitle
                )
            }
        }
}