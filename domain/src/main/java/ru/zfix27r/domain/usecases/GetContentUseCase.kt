package ru.zfix27r.domain.usecases

import androidx.lifecycle.LiveData
import ru.zfix27r.data.ContentRepositoryImpl
import ru.zfix27r.data.common.Resource
import ru.zfix27r.data.model.db.Content
import javax.inject.Inject

class GetContentUseCase @Inject constructor(
    private val contentRepository: ContentRepositoryImpl
) {
    fun execute(id: Long): LiveData<Resource<Content>> {
        return contentRepository.getContent(id)
    }
}