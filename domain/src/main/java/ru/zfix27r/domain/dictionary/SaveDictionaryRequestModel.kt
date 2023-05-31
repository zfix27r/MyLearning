package ru.zfix27r.domain.dictionary

data class SaveDictionaryRequestModel(
    val id: Long,
    val title: String,
    val subtitle: String
)