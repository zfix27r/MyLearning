package ru.sergeyzabelin.mylearning.ui.dictionary

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import ru.sergeyzabelin.mylearning.ui.dictionary.common.InputStatus
import ru.sergeyzabelin.mylearning.ui.dictionary.common.InputStatus.EMPTY
import ru.sergeyzabelin.mylearning.ui.dictionary.common.InputStatus.SUCCESS
import ru.zfix27r.domain.model.CommonReqModel
import ru.zfix27r.domain.model.CommonResModel
import ru.zfix27r.domain.model.SaveTopicReqModel
import ru.zfix27r.domain.model.TopicResModel
import ru.zfix27r.domain.usecases.AddTopicUseCase
import ru.zfix27r.domain.usecases.GetTopicUseCase
import ru.zfix27r.domain.usecases.SaveTopicUseCase
import javax.inject.Inject

@HiltViewModel
class TopicEditorViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getTopicUseCase: GetTopicUseCase,
    private val addTopicUseCase: AddTopicUseCase,
    private val saveTopicUseCase: SaveTopicUseCase
) : ViewModel() {
    private val topicId: Long = savedStateHandle.get<Long>(TOPIC_ID) ?: 0
    private val parentTopicId: Long = savedStateHandle.get<Long>(PARENT_TOPIC_ID) ?: 0

    private var title: String = ""
    private var subTitle: String = ""

    var topic: TopicResModel? = null

    fun isSaveMode() = topicId > 0

    suspend fun getTopic(): Flow<TopicResModel> {
        return getTopicUseCase.execute(CommonReqModel(topicId))
    }

    suspend fun save(): Flow<CommonResModel?> {
        val topic = SaveTopicReqModel(
            id = topicId,
            title = title,
            subTitle = subTitle,
        )
        return saveTopicUseCase.execute(topic)
    }

    fun trySetTitle(title: String): InputStatus {
        if (title.isEmpty()) return EMPTY
        this.title = title
        return SUCCESS
    }

    fun trySetSubTitle(subTitle: String): InputStatus {
        if (subTitle.isEmpty()) return EMPTY
        this.subTitle = subTitle
        return SUCCESS
    }

    fun isInputConditionsCorrectly(): Boolean {
        return (trySetTitle(title) == SUCCESS)
    }

    companion object {
        const val TOPIC_ID = "topicId"
        const val PARENT_TOPIC_ID = "parentTopicId"
    }
}