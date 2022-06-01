package ru.sergeyzabelin.mylearning.domain

import android.app.Application
import android.util.JsonReader
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import ru.sergeyzabelin.mylearning.data.MainRepository
import ru.sergeyzabelin.mylearning.data.entities.LessonTopic

class MainViewModel(application: Application) : AndroidViewModel(Application()) {
    private val repo = MainRepository(application)
    private val context = application

    init {

    }
}