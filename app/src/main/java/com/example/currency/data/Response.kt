package com.example.currency.data


import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("base")
    val base: String,
    @SerializedName("quote")
    val quote: HashMap<String, String>
)