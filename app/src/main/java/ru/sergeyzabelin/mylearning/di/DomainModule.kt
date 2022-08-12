package ru.sergeyzabelin.mylearning.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.zfix27r.data.ContentRepositoryImpl
import ru.zfix27r.data.DictionaryRepositoryImpl
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
    fun provideAddTopicUseCase(repository: DictionaryRepositoryImpl): AddTopicUseCase {
        return AddTopicUseCase(repository)
    }

    @Provides
    fun provideSaveTopicUseCase(repository: DictionaryRepositoryImpl): SaveTopicUseCase {
        return SaveTopicUseCase(repository)
    }

    @Provides
    fun provideDeleteTopicUseCase(repository: DictionaryRepositoryImpl): DeleteTopicUseCase {
        return DeleteTopicUseCase(repository)
    }

    @Provides
    fun provideGetContentUseCase(repository: ContentRepositoryImpl): GetContentUseCase {
        return GetContentUseCase(repository)
    }
}
