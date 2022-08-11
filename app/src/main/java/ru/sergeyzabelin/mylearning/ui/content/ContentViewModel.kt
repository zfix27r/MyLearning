package ru.sergeyzabelin.mylearning.ui.content

/*
@HiltViewModel
class ContentViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getContentUseCase: GetContentUseCase
) : ViewModel() {
    var topicId: Long = savedStateHandle.get<Long>(TOPIC_ID) ?: 0
    val content: LiveData<ContentResModel> =
        liveData { getContentUseCase.execute(ContentReqModel(topicId)) }

    companion object {
        const val TOPIC_ID = "topicId"
    }
}*/
