package ru.sergeyzabelin.mylearning

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = FirebaseFirestore.getInstance()

        val user: MutableMap<String, Any> = HashMap()
        user["first"] = "Ada"
        user["last"] = "Lovelace"
        user["born"] = 1815

        db.collection("lesson_topic")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(
                    "sd",
                    "DocumentSnapshot added with ID: " + documentReference.id
                )
            }
            .addOnFailureListener { e -> Log.w("TAG", "Error adding document", e) }

        db.collection("lesson_topic")
            .get()
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    for (document in task.result) {
                        Log.d("TAG", document.id + " => " + document.data)
                    }
                } else {
                    Log.w("TAG", "Error getting documents.", task.exception)
                }
            }

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
}