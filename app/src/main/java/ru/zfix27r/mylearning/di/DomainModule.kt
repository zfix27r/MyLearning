package ru.zfix27r.mylearning.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.zfix27r.data.MainRepositoryImpl
import ru.zfix27r.data.TopicEditorRepositoryImpl
import ru.zfix27r.domain.main.DeleteTopicUseCase
import ru.zfix27r.domain.main.GetTopicsByParentIdUseCase
import ru.zfix27r.domain.topicEditor.GetTopic4TopicEditorUseCase
import ru.zfix27r.domain.topicEditor.GetTopicsByTopicId4TopicEditorUseCase
import ru.zfix27r.domain.topicEditor.GetTopicsByTopicParentId4TopicEditorUseCase
import ru.zfix27r.domain.topicEditor.SaveTopic4TopicEditorUseCase

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {
    @Provides
    fun provideGetTopicsByParentIdUseCase(repository: MainRepositoryImpl) =
        GetTopicsByParentIdUseCase(repository)

    @Provides
    fun provideDeleteTopicUseCase(repository: MainRepositoryImpl) =
        DeleteTopicUseCase(repository)

    @Provides
    fun provideGetTopicUseCase(repository: TopicEditorRepositoryImpl) =
        GetTopic4TopicEditorUseCase(repository)

    @Provides
    fun provideGetTopicsByTopicId4TopicEditorUseCase(repository: TopicEditorRepositoryImpl) =
        GetTopicsByTopicId4TopicEditorUseCase(repository)

    @Provides
    fun provideGetTopicsByTopicParentId4TopicEditorUseCase(repository: TopicEditorRepositoryImpl) =
        GetTopicsByTopicParentId4TopicEditorUseCase(repository)

    @Provides
    fun provideSaveTopicUseCase(repository: TopicEditorRepositoryImpl) =
        SaveTopic4TopicEditorUseCase(repository)
}