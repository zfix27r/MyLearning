package ru.sergeyzabelin.mylearning.ui.dictionary

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import ru.zfix27r.domain.model.CommonReqModel
import ru.zfix27r.domain.model.DictionaryResModel
import ru.zfix27r.domain.usecases.GetDictionaryUseCase
import javax.inject.Inject

@HiltViewModel
class DictionaryViewModel @Inject constructor(
    private val getDictionaryUseCase: GetDictionaryUseCase
/*    private val deleteTopicUseCase: DeleteTopicUseCase*/
) : ViewModel() {

    fun getDictionary(topicId: Long): Flow<DictionaryResModel> {
        return getDictionaryUseCase.execute(CommonReqModel(topicId))
    }

/*    fun delete(model: DeleteTopicReqModel) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteTopicUseCase.execute(model)
        }
    }*/
}