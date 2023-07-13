package ru.zfix27r.mylearning.ui.profile

import android.content.Context
import android.content.res.Configuration
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import androidx.preference.PreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.zfix27r.mylearning.R
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    @ApplicationContext context: Context
) : ViewModel() {
    private val isSystemThemeDark = context.isDarkTheme()
    private val preferenceManager = PreferenceManager.getDefaultSharedPreferences(context)
    private val themeKey = context.getString(R.string.theme_key)
    private val themeDark = context.getString(R.string.theme_dark_name)
    private val themeLight = context.getString(R.string.theme_light_name)
    private val themeDefault = context.getString(R.string.theme_default_name)

    fun changeThemeMode() {
        val theme = preferenceManager.getString(themeKey, themeDefault)

        if (theme == themeDark)
            if (isSystemThemeDark) putValue(themeLight) else putValue(themeDefault)
        else
            if (isSystemThemeDark) putValue(themeDark) else putValue(themeDefault)
    }

    private fun putValue(value: String) {
        preferenceManager.edit {
            putString(themeKey, value)
        }
    }

    private fun Context.isDarkTheme(): Boolean {
        return resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    }
}