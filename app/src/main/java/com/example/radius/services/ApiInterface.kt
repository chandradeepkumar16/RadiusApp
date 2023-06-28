package com.example.radius.services

import com.example.radius.model.Facility
import retrofit2.http.GET

interface ApiInterface {
    @GET("db")
    fun getData(): retrofit2.Call<List<Facility>>
}