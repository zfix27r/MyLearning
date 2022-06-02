package ru.sergeyzabelin.mylearning.data

import android.app.Application
import android.util.Log
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import ru.sergeyzabelin.mylearning.data.entities.LessonTopic
import java.lang.Exception

class MainRepository(application: Application) {
    private val firebaseFirestore = FirebaseFirestore.getInstance()

    fun getAllLessonTopic() {
    }

    fun setLessonTopicToFirebase(lessonTopic: LessonTopic) {
        firebaseFirestore.collection("lesson_topic")
            .add(lessonTopic)
            .addOnSuccessListener { documentReference ->
                loggedSuccessRequestToFirestore(documentReference)
            }
            .addOnFailureListener { exception -> loggedFailureRequestToFirestore(exception) }
    }

    private fun loggedSuccessRequestToFirestore(documentReference: DocumentReference) {
        Log.d(
            "sd",
            "DocumentSnapshot added with ID: " + documentReference.id
        )
    }

    private fun loggedFailureRequestToFirestore(exception: Exception) {
        Log.w("TAG", "Error adding document", exception)
    }
}