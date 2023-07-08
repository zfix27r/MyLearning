package ru.zfix27r.mylearning.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.zfix27r.data.MainRepositoryImpl
import ru.zfix27r.data.QuoteRepositoryImpl
import ru.zfix27r.data.TopicRepositoryImpl
import ru.zfix27r.domain.editor.quote.GetQuoteEditorModelUseCase
import ru.zfix27r.domain.editor.quote.SaveQuoteEditorModelUseCase
import ru.zfix27r.domain.editor.topic.DeleteTopicUseCase
import ru.zfix27r.domain.main.GetMainModelsUseCase
import ru.zfix27r.domain.search.SearchUseCase
import ru.zfix27r.domain.editor.topic.GetTopicEditorModelUseCase
import ru.zfix27r.domain.editor.topic.parent.GetTopicEditorParentModelByTopicIdUseCase
import ru.zfix27r.domain.editor.topic.parent.GetTopicEditorParentModelByTopicParentIdUseCase
import ru.zfix27r.domain.editor.topic.SaveTopicEditorModelUseCase

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {
    @Provides
    fun provideGetMainModelsUseCase(repositoryImpl: MainRepositoryImpl) =
        GetMainModelsUseCase(repositoryImpl)

    @Provides
    fun provideGetTopicEditorTopicUseCase(repository: TopicRepositoryImpl) =
        GetTopicEditorModelUseCase(repository)

    @Provides
    fun provideGetTopicEditorParentTopicsUseCase(repository: TopicRepositoryImpl) =
        GetTopicEditorParentModelByTopicIdUseCase(repository)

    @Provides
    fun provideGetTopicEditorParentTopicsByParentUseCase(repository: TopicRepositoryImpl) =
        GetTopicEditorParentModelByTopicParentIdUseCase(repository)

    @Provides
    fun provideSaveTopicEditorTopicUseCase(repository: TopicRepositoryImpl) =
        SaveTopicEditorModelUseCase(repository)

    @Provides
    fun provideDeleteTopicUseCase(repositoryImpl: TopicRepositoryImpl) =
        DeleteTopicUseCase(repositoryImpl)

    @Provides
    fun provideSearchUseCase(repositoryImpl: MainRepositoryImpl) =
        SearchUseCase(repositoryImpl)

    @Provides
    fun provideGetQuoteEditorModelUseCase(repositoryImpl: QuoteRepositoryImpl) =
        GetQuoteEditorModelUseCase(repositoryImpl)

    @Provides
    fun provideSaveQuoteEditorModelUseCase(repositoryImpl: QuoteRepositoryImpl) =
        SaveQuoteEditorModelUseCase(repositoryImpl)
}