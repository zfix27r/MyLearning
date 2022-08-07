package ru.sergeyzabelin.mylearning.ui.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.zfix27r.data.common.Resource
import ru.zfix27r.data.model.db.Content
import ru.sergeyzabelin.mylearning.domain.usecases.GetContentUseCase
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getContentUseCase: GetContentUseCase
) : ViewModel() {
    var topicId: Long = savedStateHandle.get<Long>("topicId") ?: 0
    val content: LiveData<Resource<Content>> = getContentUseCase.execute(topicId)
}