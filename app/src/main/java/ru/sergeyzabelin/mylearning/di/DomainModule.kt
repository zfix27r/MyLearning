package ru.sergeyzabelin.mylearning.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.sergeyzabelin.mylearning.data.DictionaryRepository
import ru.sergeyzabelin.mylearning.di.usecases.GetDictionaryTopicsUseCase

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    fun provideGetDictionaryUseCase(dictionaryRepository: DictionaryRepository): GetDictionaryTopicsUseCase {
        return GetDictionaryTopicsUseCase(dictionaryRepository)
    }

}