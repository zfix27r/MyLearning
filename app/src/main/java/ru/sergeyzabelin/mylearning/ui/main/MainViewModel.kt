package ru.sergeyzabelin.mylearning.ui.main

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.sergeyzabelin.mylearning.data.local.db.AppDatabase
import ru.sergeyzabelin.mylearning.data.local.db.MainRepository


class MainViewModel(application: Application) : AndroidViewModel(Application()) {
    private val repo = MainRepository(AppDatabase.getInstance(application).mainDao())

    val loadingVisibility: ObservableInt = ObservableInt(8)
    val noResultVisibility: ObservableInt = ObservableInt(8)

    //val dictionaryList: LiveData<List<Dictionary>> = repo.getAllDictionary()

    var collectionId: String? = null

    val isReady: ObservableBoolean = ObservableBoolean(false)

    init {
        viewModelScope.launch {
            repo.firstRun()
        }
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