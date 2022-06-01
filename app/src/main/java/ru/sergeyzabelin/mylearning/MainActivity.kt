package ru.sergeyzabelin.mylearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.JsonReader
import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import ru.sergeyzabelin.mylearning.data.entities.LessonTopic
import ru.sergeyzabelin.mylearning.data.entities.SerializerLessonTopic

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Firebase.app
/*        this.assets.open("lesson_topic.json").use { inputStream ->
            JsonReader(inputStream.reader()).use { jsonReader ->
                Log.e("asdad", Json.decodeFromString<SerializerLessonTopic>(jsonReader.toString()).toString())
*//*                    val type = object : TypeToken<List<LessonTopic>>() {}.type
                    val list: List<LessonTopic> = Gson().fromJson(jsonReader, type)
                    dao.setAllStoryTitle(list)*//*
            }
        }
    }*/

}