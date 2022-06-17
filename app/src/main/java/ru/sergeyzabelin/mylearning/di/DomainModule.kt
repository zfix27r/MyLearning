package ru.sergeyzabelin.mylearning.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.sergeyzabelin.mylearning.data.DictionaryRepository
import ru.sergeyzabelin.mylearning.di.domain.usecases.GetDictionaryUseCase

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    fun provideGetDictionaryUseCase(dictionaryRepository: DictionaryRepository): GetDictionaryUseCase {
        return GetDictionaryUseCase(dictionaryRepository)
    }

}