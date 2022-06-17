package ru.sergeyzabelin.mylearning.ui.dictionary

import android.os.Bundle
import androidx.databinding.ObservableInt
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.sergeyzabelin.mylearning.data.Resource
import ru.sergeyzabelin.mylearning.data.model.db.Topic
import ru.sergeyzabelin.mylearning.di.domain.usecases.GetDictionaryUseCase

class DictionaryViewModel @AssistedInject constructor(
    getDictionaryUseCase: GetDictionaryUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle,
    @Assisted private val topicId: Long
) : ViewModel() {

/*    private val topicId: Long
        get() {
            return if (savedStateHandle.get<Long>("topicId") == null) {
                0
            } else {
                savedStateHandle.get<Long>("topicId")!!
            }
        }*/
    val topics: LiveData<Resource<List<Topic>>> = getDictionaryUseCase.execute(topicId)

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

    @AssistedFactory
    interface DictionaryViewModelFactory {
        fun create(handle: SavedStateHandle, topicId: Long): DictionaryViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: DictionaryViewModelFactory,
            owner: SavedStateRegistryOwner,
            defaultArgs: Bundle? = null,
            topicId: Long
        ): AbstractSavedStateViewModelFactory =
            object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    key: String,
                    modelClass: Class<T>,
                    handle: SavedStateHandle
                ): T {
                    return assistedFactory.create(handle, topicId) as T
                }
            }
    }
}