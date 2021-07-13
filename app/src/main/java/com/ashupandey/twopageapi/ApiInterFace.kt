package com.ashupandey.twopageapi

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterFace {
    @GET("users")
    suspend fun getData(
            @Query("page") page : Int = 1
    ):Response<RecivedData>?
}