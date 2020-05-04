package com.example.robustaweather.data

import com.example.nousapp.data.model.NousResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface  NousApis {

    @GET("s/Njedq4WpjWz4KKk/download")
    fun getJsonData(): Call<NousResponse>
}