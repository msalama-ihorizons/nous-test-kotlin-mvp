package com.example.nousapp.data

import androidx.lifecycle.LiveData
import com.example.nousapp.data.model.NousResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NousApis {

       @GET("s/Njedq4WpjWz4KKk/download")
       suspend fun getJsonData(): NousResponse//ApiResponse<NousResponse>
  /*  @GET("s/Njedq4WpjWz4KKk/download")
    suspend fun getJsonData(): ApiResponse<NousResponse>*/

}