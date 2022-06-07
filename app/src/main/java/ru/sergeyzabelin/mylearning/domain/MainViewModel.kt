package ru.sergeyzabelin.mylearning.domain

import android.app.Application
import androidx.databinding.ObservableInt
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import ru.sergeyzabelin.mylearning.data.MainRepository
import ru.sergeyzabelin.mylearning.data.entities.Dictionary


class MainViewModel(application: Application) : AndroidViewModel(Application()) {
    private val repo = MainRepository(application)

    val loadingVisibility: ObservableInt = ObservableInt(8)
    val noResultVisibility: ObservableInt = ObservableInt(8)

    val dictionaryList: LiveData<List<Dictionary>> = repo.getAllDictionary()

    var collectionId: String? = null

    init {

    }

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