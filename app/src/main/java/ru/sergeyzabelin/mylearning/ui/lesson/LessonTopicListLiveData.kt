package ru.sergeyzabelin.mylearning.ui.lesson

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.*
import ru.sergeyzabelin.mylearning.data.model.other.Lesson


class LessonTopicListLiveData(private val documentReference: DocumentReference?) :
    LiveData<List<Lesson>>(), EventListener<DocumentSnapshot> {

    private val lessonTopicListTemp: MutableList<Lesson> = mutableListOf()
    private val lessonTopicList: MutableLiveData<List<Lesson>> =
        MutableLiveData<List<Lesson>>()

    private var listenerRegistration = ListenerRegistration {}

    override fun onActive() {
        listenerRegistration = documentReference!!.addSnapshotListener(this)
        super.onActive()
    }

    override fun onInactive() {
        listenerRegistration.remove()
        super.onInactive()
    }

    override fun onEvent(
        documentSnapshot: DocumentSnapshot?,
        e: FirebaseFirestoreException?
    ) {
        Log.e("dsf", documentSnapshot!!.data.toString())

        if (documentSnapshot != null && documentSnapshot.exists()) {
            val listItems = documentSnapshot.data

            lessonTopicListTemp.clear()

            for (item in listItems!!) {
                Log.e("sd", item.toString())
                //lessonTopicListTemp.add(LessonTopic(value))
            }

            lessonTopicList.value = lessonTopicListTemp
        }
    }
}