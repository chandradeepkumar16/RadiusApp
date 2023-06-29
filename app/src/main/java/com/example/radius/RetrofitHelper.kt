package com.example.radius

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    val BASEURL = "https://my-json-server.typicode.com/iranjith4/ad-assignment/"

    fun getInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}