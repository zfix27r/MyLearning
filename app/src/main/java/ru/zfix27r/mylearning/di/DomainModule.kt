package ru.sergeyzabelin.zfix27r.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.zfix27r.data.ContentRepositoryImpl
import ru.zfix27r.data.DictionaryRepositoryImpl
import ru.zfix27r.domain.dictionary.AddDictionaryUseCase
import ru.zfix27r.domain.dictionary.DeleteDictionaryUseCase
import ru.zfix27r.domain.dictionary.GetDictionaryUseCase
import ru.zfix27r.domain.dictionary.SaveDictionaryUseCase
import ru.zfix27r.domain.usecases.*

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    fun provideGetDictionaryUseCase(repository: DictionaryRepositoryImpl): GetDictionaryUseCase {
        return GetDictionaryUseCase(repository)
    }

    @Provides
    fun provideGetTopicUseCase(repository: DictionaryRepositoryImpl): GetTopicUseCase {
        return GetTopicUseCase(repository)
    }

    @Provides
    fun provideAddTopicUseCase(repository: DictionaryRepositoryImpl): AddDictionaryUseCase {
        return AddDictionaryUseCase(repository)
    }

    @Provides
    fun provideSaveTopicUseCase(repository: DictionaryRepositoryImpl): SaveDictionaryUseCase {
        return SaveDictionaryUseCase(repository)
    }

    @Provides
    fun provideDeleteTopicUseCase(repository: DictionaryRepositoryImpl): DeleteDictionaryUseCase {
        return DeleteDictionaryUseCase(repository)
    }

    @Provides
    fun provideGetContentUseCase(repository: ContentRepositoryImpl): GetContentUseCase {
        return GetContentUseCase(repository)
    }
}
