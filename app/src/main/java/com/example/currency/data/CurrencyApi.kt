package com.example.currency.data

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Query

interface CurrencyApi {
    @GET("api/v1/forex/rates")
    fun getRates(@Query("base") base: String, @Query("token")
    token: String, @Query("date") date: String = ""): Call<Response>
}