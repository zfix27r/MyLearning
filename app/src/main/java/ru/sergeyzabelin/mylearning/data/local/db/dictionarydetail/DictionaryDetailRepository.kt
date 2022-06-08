package ru.sergeyzabelin.mylearning.data.local.db.dictionarydetail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.sergeyzabelin.mylearning.data.local.db.AppDatabase
import ru.sergeyzabelin.mylearning.data.model.db.Dictionary

class DictionaryDetailRepository(application: Application) {
    private val dao = AppDatabase.getInstance(application).dictionaryDetailDao()

    private var dictionary: MutableLiveData<Dictionary> = MutableLiveData()
    fun getDictionary(): LiveData<Dictionary> = dictionary

    suspend fun getDictionaryDetailById(id: Int) {
        dictionary.postValue(dao.getDictionaryDetailById(id))
    }
}