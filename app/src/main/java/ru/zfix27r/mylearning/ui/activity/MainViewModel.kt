package ru.zfix27r.mylearning.ui.activity

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.preference.PreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import ru.zfix27r.domain.activity.SearchModel
import ru.zfix27r.domain.activity.SearchUseCase
import ru.zfix27r.mylearning.R
import ru.zfix27r.mylearning.ui.base.BaseViewModel
import ru.zfix27r.mylearning.ui.getErrorStringRes
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val search: SearchUseCase,
) : BaseViewModel() {
    val preferenceManager: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    val themeKey = context.getString(R.string.theme_key)
    private val themeDefault = context.getString(R.string.theme_default_name)
    val themeLight = context.getString(R.string.theme_light_name)
    val themeDark = context.getString(R.string.theme_dark_name)

    private val _response = MutableLiveData<List<SearchModel>>()
    val response: LiveData<List<SearchModel>> = _response

    fun search(query: String) = viewModelScope.launch(Dispatchers.IO) {
        if (query.isNotEmpty())
            search.execute(query)
                .onStart { start() }
                .catch { error(it.getErrorStringRes()) }
                .collectLatest {
                    _response.postValue(it)
                    success()
                }
    }

    fun searchClear() {
        _response.value = listOf()
    }

    fun getCurrentTheme() = preferenceManager.getString(themeKey, themeDefault)
}