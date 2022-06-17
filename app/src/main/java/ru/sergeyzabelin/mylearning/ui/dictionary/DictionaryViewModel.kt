package ru.sergeyzabelin.mylearning.ui.dictionary

import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.sergeyzabelin.mylearning.data.DictionaryRepository
import ru.sergeyzabelin.mylearning.data.Resource
import ru.sergeyzabelin.mylearning.data.model.db.Topic
import javax.inject.Inject

class DictionaryViewModel @Inject constructor(repository: DictionaryRepository) : ViewModel() {

    var topicId: Long = 0

    val topics: LiveData<Resource<List<Topic>>> = repository.getTopicByParentId(topicId)

    //  = 0 - false, > 0 - true
/*    private var loadingCounter: Int = 0
        set(value) {
            field = value
            isLoadingCounter.set(field)
        }*/
    val isLoadingCounter: ObservableInt = ObservableInt(0)

/*    fun getNextDictionaryGroup() = viewModelScope.launch {
        loadingCounter++
        _topics = repos.getTopicByParentId(topicId)
        loadingCounter--
    }*/
}