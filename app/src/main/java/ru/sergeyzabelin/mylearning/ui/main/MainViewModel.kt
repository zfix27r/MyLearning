package ru.sergeyzabelin.mylearning.ui.main

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.sergeyzabelin.mylearning.data.DictionaryPreferences
import ru.sergeyzabelin.mylearning.data.local.db.MainRepository
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val dictionaryPreferences: DictionaryPreferences,
    repository: MainRepository
) : ViewModel() {

/*    val firstRun = {
        repository.firstRun()
    }*/

    val loadingVisibility: ObservableInt = ObservableInt(8)
    val noResultVisibility: ObservableInt = ObservableInt(8)

    //val dictionaryList: LiveData<List<Dictionary>> = repo.getAllDictionary()

    var collectionId: String? = null

    val isReady: ObservableBoolean = ObservableBoolean(false)

/*    fun getNextCollection() = repo.getNextFirebaseCollectionById(collectionId)
    fun getLesson() = repo.getLessonFromFirebase()
    fun getLessonDetail() = repo.getLessonDetailFromFirebase()
    fun getDictionary() = repo.getDictionaryFromFirebase()

    fun setLesson(lesson: Lesson) {
        repo.setLessonToFirebase(lesson)
    }*/

/*    fun setDictionary(dictionary: Dictionary) {
        repo.setDictionaryToFirebase(dictionary)
    }*/

    fun isSimpleMode(): Boolean =
        dictionaryPreferences.getModeView() == DictionaryPreferences.MODE.SIMPLE
}