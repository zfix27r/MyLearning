package ru.sergeyzabelin.mylearning.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.zfix27r.data.DictionaryRepositoryImpl
import ru.zfix27r.domain.usecases.GetDictionaryUseCase

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    fun provideGetDictionaryUseCase(repository: DictionaryRepositoryImpl): GetDictionaryUseCase {
        return GetDictionaryUseCase(repository)
    }

/*
    @Provides
    fun provideGetContentUseCase(repository: ContentRepositoryImpl): GetContentUseCase {
        return GetContentUseCase(repository)
    }
*/


}
