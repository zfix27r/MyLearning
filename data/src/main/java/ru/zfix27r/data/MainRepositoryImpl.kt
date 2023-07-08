package ru.zfix27r.data

import kotlinx.coroutines.flow.map
import ru.zfix27r.data.local.dao.MainDao
import ru.zfix27r.data.local.model.GetMainDataModel
import ru.zfix27r.domain.MainRepository
import ru.zfix27r.domain.main.GetMainModel
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val dao: MainDao,
) : MainRepository {
    override fun getMainModels() = dao.getMain().map { it.toListGetMainQuotesModel() }
    private fun List<GetMainDataModel>.toListGetMainQuotesModel() =
        map {
            it.run {
                GetMainModel(
                    quoteId,
                    quoteDescription,
                    topicId,
                    topicIconId,
                    topicTitle,
                    topicSubtitle
                )
            }
        }
}