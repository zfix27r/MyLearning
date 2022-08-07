package ru.zfix27r.data.remote

import retrofit2.http.GET
import retrofit2.http.QueryMap
import ru.zfix27r.data.model.api.WikiDetail


interface WikiApi {
    @GET("/w/api.php")
    suspend fun getDetail(@QueryMap id: Map<String, String>): WikiDetail
}