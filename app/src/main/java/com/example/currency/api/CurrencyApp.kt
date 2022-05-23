package com.example.currency.api

import com.example.currency.data.CurrencyApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object CurrencyApp {
    fun create() : CurrencyApi {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://finnhub.io/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(CurrencyApi::class.java)
    }
}