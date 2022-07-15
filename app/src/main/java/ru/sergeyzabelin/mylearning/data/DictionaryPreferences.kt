package ru.sergeyzabelin.mylearning.data

import android.content.Context
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DictionaryPreferences @Inject constructor(@ApplicationContext context: Context) {
    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    fun getModeView(): MODE {
        return when (prefs.getInt(MODE_VIEW, 0)) {
            1 -> MODE.EXTENDED
            else -> MODE.SIMPLE
        }
    }

    fun setModeView(mode: MODE) {
        prefs.edit().putInt(MODE_VIEW, mode.ordinal).apply()
    }

    enum class MODE {
        SIMPLE, EXTENDED
    }

    companion object {
        const val MODE_VIEW = "mode_view"
    }
}