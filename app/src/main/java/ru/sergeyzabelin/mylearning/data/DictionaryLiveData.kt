package ru.sergeyzabelin.mylearning.data

import androidx.lifecycle.LiveData
import ru.sergeyzabelin.mylearning.data.model.db.Dictionary

class DictionaryLiveData : LiveData<List<Dictionary>>() {
    override fun onActive() {


        super.onActive()
    }
}