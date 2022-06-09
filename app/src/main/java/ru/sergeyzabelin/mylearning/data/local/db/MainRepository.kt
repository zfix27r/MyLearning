package ru.sergeyzabelin.mylearning.data.local.db


class MainRepository(private val dao: MainDao) {

    //private val firebaseFirestore = FirebaseFirestore.getInstance()
    //private val settings = application.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)

    suspend fun firstRun() = dao.firstRun()

/*    fun getPatternFromFirebase(): Query {
        return firebaseFirestore.collection(FIREBASE_PATTERN_COLLECTION)
            .limit(LIMIT.toLong())
    }

    fun getNextFirebaseCollectionById(id: String?): Query {
        return firebaseFirestore.collection(FIREBASE_COLLECTION_MAIN)
            .limit(LIMIT.toLong())
    }

    fun getLessonFromFirebase(): Query {
        return firebaseFirestore.collection(FIREBASE_COLLECTION)
            .document(FIREBASE_LESSON_DOCUMENT)
            .collection(FIREBASE_LESSON_COLLECTION)
            .limit(LIMIT.toLong())
    }

    fun getLessonDetailFromFirebase(): Query {
        return firebaseFirestore.collection(FIREBASE_COLLECTION)
            .document(FIREBASE_LESSON_DETAIL_DOCUMENT)
            .collection(FIREBASE_LESSON_DETAIL_COLLECTION)
            .limit(LIMIT.toLong())
    }

    fun getDictionaryFromFirebase(): Query {
        return firebaseFirestore.collection(FIREBASE_COLLECTION)
            .document(FIREBASE_DICTIONARY_DOCUMENT)
            .collection(FIREBASE_DICTIONARY_COLLECTION)
            .limit(LIMIT.toLong())
    }

    fun setLessonToFirebase(lesson: Lesson) {
        firebaseFirestore.collection(FIREBASE_COLLECTION)
            .document(FIREBASE_LESSON_DOCUMENT)
            .collection(FIREBASE_LESSON_COLLECTION)
            .add(lesson)
            .addOnSuccessListener { documentReference ->
                loggedSuccessRequestToFirestore(documentReference)
            }
            .addOnFailureListener { exception -> loggedFailureRequestToFirestore(exception) }
    }*/

/*    fun setDictionaryToFirebase(dictionary: Dictionary) {
        firebaseFirestore.collection(FIREBASE_COLLECTION)
            .document(FIREBASE_DICTIONARY_DOCUMENT)
            .collection(FIREBASE_DICTIONARY_COLLECTION)
            .document(dictionary.titleOriginal!!)
            .set(dictionary)
*//*
            .doc()
            .set(dictionary)
*//*
*//*            .addOnSuccessListener { documentReference ->
                loggedSuccessRequestToFirestore(documentReference)
            }
            .addOnFailureListener { exception -> loggedFailureRequestToFirestore(exception) }*//*
    }*/

/*    private fun loggedSuccessRequestToFirestore(documentReference: DocumentReference) {
        Log.e(
            "sd",
            documentReference.id
        )
    }

    private fun loggedFailureRequestToFirestore(exception: Exception) {
        Log.e("TAG", "Error adding document", exception)
    }*/



    companion object {
        const val FIREBASE_COLLECTION_MAIN = "myLearningApp"


        const val FIREBASE_PATTERN_COLLECTION = "pattern"
        const val FIREBASE_DICTIONARY_COLLECTION = "dictionary"

        const val FIREBASE_COLLECTION = "my_learning_app"
        const val FIREBASE_LESSON_DOCUMENT = "lesson"
        const val FIREBASE_LESSON_COLLECTION = "list"
        const val FIREBASE_LESSON_DETAIL_DOCUMENT = "lesson_detail"
        const val FIREBASE_LESSON_DETAIL_COLLECTION = "list"
        const val FIREBASE_DICTIONARY_DOCUMENT = "dictionary"
        //const val FIREBASE_DICTIONARY_COLLECTION = "list"
        const val LIMIT = 50

        const val PREFERENCES = "settings"
    }
}