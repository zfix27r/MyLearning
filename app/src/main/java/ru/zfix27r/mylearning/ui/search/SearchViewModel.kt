package ru.zfix27r.mylearning.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.zfix27r.domain.search.SearchModel
import ru.zfix27r.domain.search.SearchUseCase
import ru.zfix27r.mylearning.ui.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchByString: SearchUseCase,
) : BaseViewModel() {

    private val _result = MutableLiveData<List<SearchAdapterModel>>()
    val result: LiveData<List<SearchAdapterModel>> = _result

    fun search(string: String) = viewModelScope.launch(Dispatchers.IO) {
/*        searchByString
            .execute(string)
            .catch { onError(it.getErrorStringRes()) }
            .collectLatest {
                _result.postValue(it.toListSearchAdapterModel())
            }*/
    }

    private fun List<SearchModel>.toListSearchAdapterModel() =
        map {
            when (it) {
                is SearchModel.Topic -> it.toSearchAdapterModelTopic()
            }
        }

    private fun SearchModel.Topic.toSearchAdapterModelTopic() =
        SearchAdapterModel.Topic(id, title, subtitle)

    fun loading(id: Int): Nothing = TODO("Not yet implemented")
}