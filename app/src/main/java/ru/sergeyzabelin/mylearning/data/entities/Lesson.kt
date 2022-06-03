package ru.sergeyzabelin.mylearning.data.entities

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class Lesson(
    var title: String? = null,
    var description: String? = null
) {
    companion object {

    }
}
