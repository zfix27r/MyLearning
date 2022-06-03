package ru.sergeyzabelin.mylearning.domain

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.sergeyzabelin.mylearning.data.MainRepository
import ru.sergeyzabelin.mylearning.data.entities.Lesson


class MainViewModel(application: Application) : AndroidViewModel(Application()) {
    private val repo = MainRepository(application)

    init {

    }

    fun getLesson() = repo.getLessonFromFirebase()
    fun getLessonDetail() = repo.getLessonDetailFromFirebase()

    fun setLesson(lesson: Lesson) {
        repo.setLessonToFirebase(lesson)
    }
}