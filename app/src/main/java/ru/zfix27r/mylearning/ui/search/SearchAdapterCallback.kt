package ru.zfix27r.mylearning.ui.search

import ru.zfix27r.domain.activity.SearchModel

interface SearchAdapterCallback {
    fun onClick(searchModel: SearchModel)
}