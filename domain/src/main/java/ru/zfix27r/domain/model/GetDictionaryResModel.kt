package ru.zfix27r.domain.model

data class GetDictionaryResModel(
    val topic: Topic,
    val topics: List<Topics>
) {
    data class Topic(
        val title: String,
        val subTitle: String?
    )

    data class Topics(
        val id: Long,
        val parentId: Long,
        val title: String,
        val subTitle: String?,
        val difficulty: Int?
    )
}