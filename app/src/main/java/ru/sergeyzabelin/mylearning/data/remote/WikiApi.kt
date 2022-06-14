package ru.sergeyzabelin.mylearning.data.remote

import retrofit2.http.GET
import retrofit2.http.QueryMap
import ru.sergeyzabelin.mylearning.data.model.api.WikiDetail


interface WikiApi {
    @GET("/w/api.php")
    suspend fun getDetail(@QueryMap id: Map<String, String>): WikiDetail
}