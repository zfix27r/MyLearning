package ru.zfix27r.domain.dictionary

data class AddDictionaryRequestModel(
    val parentId: Long,
    val title: String,
    val subtitle: String,
    val difficulty: Int
)
