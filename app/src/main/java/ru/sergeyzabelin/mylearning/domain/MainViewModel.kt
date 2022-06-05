package ru.sergeyzabelin.mylearning.domain

import android.app.Application
import androidx.databinding.ObservableInt
import androidx.lifecycle.AndroidViewModel
import ru.sergeyzabelin.mylearning.data.MainRepository
import ru.sergeyzabelin.mylearning.data.entities.Dictionary
import ru.sergeyzabelin.mylearning.data.entities.Lesson


class MainViewModel(application: Application) : AndroidViewModel(Application()) {
    private val repo = MainRepository(application)

    val loadingVisibility: ObservableInt = ObservableInt(8)
    val noResultVisibility: ObservableInt = ObservableInt(8)

    var collectionId: String? = null

    init {

    }

    fun getNextCollection() = repo.getNextFirebaseCollectionById(collectionId)
    fun getLesson() = repo.getLessonFromFirebase()
    fun getLessonDetail() = repo.getLessonDetailFromFirebase()
    fun getDictionary() = repo.getDictionaryFromFirebase()

    fun setLesson(lesson: Lesson) {
        repo.setLessonToFirebase(lesson)
    }

    fun setDictionary(dictionary: Dictionary) {
        repo.setDictionaryToFirebase(dictionary)
    }
}