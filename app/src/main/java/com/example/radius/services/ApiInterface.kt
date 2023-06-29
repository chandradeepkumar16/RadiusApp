package com.example.radius.services

import com.example.radius.model.FacilityModel
import retrofit2.http.GET

interface ApiInterface {
//    @GET("db")
//    fun getData(): retrofit2.Call<List<Facility>>
    @GET("db")
     fun getFacilities(): retrofit2.Call<FacilityModel>


}