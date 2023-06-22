package ru.zfix27r.mylearning.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.zfix27r.domain.main.DeleteTopicUseCase
import ru.zfix27r.domain.main.GetTopicsByParentIdModel
import ru.zfix27r.domain.main.GetTopicsByParentIdUseCase
import ru.zfix27r.mylearning.ui.BaseViewModel
import ru.zfix27r.mylearning.ui.getErrorStringRes
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTopicsByParentIdUseCase: GetTopicsByParentIdUseCase,
    private val deleteTopicUseCase: DeleteTopicUseCase,
) : BaseViewModel() {
    private var _topicParentId: Int = 0
    val topicParentId
        get() = _topicParentId

    private var _data: MutableLiveData<List<MainUIModel>> = MutableLiveData()
    val data: LiveData<List<MainUIModel>> = _data

    private var _itemError: MutableLiveData<Throwable> = MutableLiveData()
    val itemError: LiveData<Throwable> = _itemError

    override fun loading(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        _topicParentId = id
        getTopicsByParentIdUseCase
            .execute(id)
            .catch { onError(it.getErrorStringRes()) }
            .collectLatest {
                _data.postValue(it.toListTopicMainUIModel())
            }
    }

    private fun List<GetTopicsByParentIdModel>.toListTopicMainUIModel(): List<MainUIModel> {
        val mutableList: MutableList<MainUIModel> =
            map { it.run { MainUIModel.Topic(id, title, subtitle, difficulty) } }
                .toMutableList()

        mutableList.add(MainUIModel.Add())

        return mutableList.toList()
    }

    fun delete(id: Int) = viewModelScope.launch(Dispatchers.IO) {
/*        _data.value?.find { it.id == id }?.let { item ->
            deleteTopicUseCase
                .execute(id)
                .onStart {
                    item.isBusy = true
                }
                .catch {
                    _itemError.value = it
                    item.isBusy = false
                }
        } ?: throw Exception("Item by id is not found")*/
    }
}