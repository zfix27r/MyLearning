package ru.sergeyzabelin.mylearning.data

import android.app.Application
import android.util.Log
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import ru.sergeyzabelin.mylearning.data.entities.Lesson


class MainRepository(application: Application) {
    private val firebaseFirestore = FirebaseFirestore.getInstance()

    fun getLessonFromFirebase(): Query {
        return firebaseFirestore.collection(FIREBASE_LESSON_PATH)
            .limit(LIMIT.toLong())
    }

    fun getLessonDetailFromFirebase(): Query {
        return firebaseFirestore.collection(FIREBASE_LESSON_DETAIL_PATH)
            .limit(LIMIT.toLong())
    }

    fun setLessonToFirebase(lesson: Lesson) {
        firebaseFirestore.collection(FIREBASE_LESSON_PATH)
            .add(lesson)
            .addOnSuccessListener { documentReference ->
                loggedSuccessRequestToFirestore(documentReference)
            }
            .addOnFailureListener { exception -> loggedFailureRequestToFirestore(exception) }
    }

    private fun loggedSuccessRequestToFirestore(documentReference: DocumentReference) {
        Log.e(
            "sd",
             documentReference.id
        )
    }

    private fun loggedFailureRequestToFirestore(exception: Exception) {
        Log.e("TAG", "Error adding document", exception)
    }


/*    fun getDetail(movieId: String?): LiveData<Resource<MyBeanClass>>? {
        val myBeanClass: MutableLiveData<Resource<MyBeanClass>> =
            MutableLiveData<Resource<MyBeanClass>>()
        val apiInterface: ApiInterface = ApiClient().getClient().create(ApiInterface::class.java)
        val call: Call<MyBeanClass> = apiInterface.getData(id)
        call.enqueue(object : Callback<MyBeanClass?>() {
            fun onResponse(call: Call<MyBeanClass?>?, response: Response<MyBeanClass?>) {
                if (response.body() != null) {
                    val body: MyBeanClass = response.body()
                    myBeanClass.setValue(Resource.success(body))
                }
            }

            fun onFailure(call: Call<MyBeanClass?>?, t: Throwable) {
                myBeanClass.setValue(Resource.error(t.message, null))
            }
        })
        return myBeanClass
    }*/

    companion object {
        const val FIREBASE_LESSON_PATH = "lesson"
        const val FIREBASE_LESSON_DETAIL_PATH = "lesson_detail"
        const val LIMIT = 50
    }
}