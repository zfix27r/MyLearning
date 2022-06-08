package ru.sergeyzabelin.mylearning.data.local.db.dictionary

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.sergeyzabelin.mylearning.data.local.db.AppDatabase
import ru.sergeyzabelin.mylearning.data.model.db.Dictionary

class DictionaryRepository(application: Application) {
    private val dao = AppDatabase.getInstance(application).dictionaryDao()

    private var dictionaryGroup: MutableLiveData<List<Dictionary>> = MutableLiveData()

    fun getDictionaryGroup(): LiveData<List<Dictionary>> = dictionaryGroup

    suspend fun getAllDictionary() {
        dictionaryGroup.postValue(dao.getAllDictionary())
    }


}