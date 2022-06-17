package ru.sergeyzabelin.mylearning.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.sergeyzabelin.mylearning.ui.dictionary.DictionaryViewModel
import ru.sergeyzabelin.mylearning.ui.dictionary.DictionaryViewModelFactory
import ru.sergeyzabelin.mylearning.ui.main.MainViewModel

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DictionaryViewModel::class)
    abstract fun bindDictionaryViewModel(dictionaryViewModel: DictionaryViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: DictionaryViewModelFactory): ViewModelProvider.Factory
}