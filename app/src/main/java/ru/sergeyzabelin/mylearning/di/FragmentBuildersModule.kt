package ru.sergeyzabelin.mylearning.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.sergeyzabelin.mylearning.ui.dictionary.DictionaryFragment
import ru.sergeyzabelin.mylearning.ui.main.MainFragment

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun contributeDictionaryFragment(): DictionaryFragment
}