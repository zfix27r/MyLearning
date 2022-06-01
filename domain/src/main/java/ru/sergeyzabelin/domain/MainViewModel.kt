package ru.sergeyzabelin.domain

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.sergeyzabelin.data.MainRepository

class MainViewModel(application: Application) : AndroidViewModel(Application()) {
    private val repo = MainRepository(application)

    init {
        repo.getAllLessonTopic()
    }
}