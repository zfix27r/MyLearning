package ru.sergeyzabelin.mylearning.ui.main

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import ru.sergeyzabelin.mylearning.data.local.db.MainRepository
import javax.inject.Inject


class MainViewModel @Inject constructor(repository: MainRepository) : ViewModel() {

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
}