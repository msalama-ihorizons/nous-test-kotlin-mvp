package com.example.nousapp.list

import com.example.nousapp.data.model.NousResponse
import com.example.robustaweather.OnFinishedListener
import com.example.robustaweather.data.NousApis
import com.example.robustaweather.data.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemsListFetcher {


    fun getItems(listener: OnFinishedListener<NousResponse>
    ) {

        val request = ServiceBuilder.ServiceBuilder.buildService(NousApis::class.java)
        val call = request.getJsonData()

        call.enqueue(object : Callback<NousResponse> {
            override fun onResponse(call: Call<NousResponse>, response: Response<NousResponse>) {

                listener.onComplete()

                if (response.isSuccessful){
                    listener.onSuccess(response.body())
                }
            }
            override fun onFailure(call: Call<NousResponse>, t: Throwable) {

                listener.onComplete()

                listener.onFailure(t.message)
            }
        })

    }
}