package com.example.nousapp.itemlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import com.example.nousapp.data.model.NousResponse
import com.example.nousapp.OnFinishedListener
import com.example.nousapp.data.ApiEmptyResponse
import com.example.nousapp.data.ApiErrorResponse
import com.example.nousapp.data.ApiSuccessResponse
import com.example.nousapp.data.NousApis
import com.example.nousapp.data.model.Resource
import com.example.robustaweather.data.ServiceBuilder

class ItemsListFetcher {


    fun getItems(): LiveData<Resource<NousResponse>> {

        val result = MediatorLiveData<Resource<NousResponse>>()

        result.value  = Resource.loading(null)

        val request = ServiceBuilder.ServiceBuilder.buildService(NousApis::class.java)
        val apiResponseLiveData = request.getJsonData()

        result.addSource(apiResponseLiveData) {
            response ->
            when (response) {
                is ApiSuccessResponse -> result.value = Resource.success<NousResponse>(response.body)
                is ApiEmptyResponse -> result.value = Resource.success(null)
                is ApiErrorResponse -> result.value = Resource.error(response.errorMessage, null);
            }
        }


       /* return Transformations.map(apiResponseLiveData) { response ->
            when (response) {
                is ApiSuccessResponse -> Resource.success<NousResponse>(response.body)
                is ApiEmptyResponse -> Resource.success(null)
                is ApiErrorResponse -> Resource.error(response.errorMessage, null);
            }
        }*/

        return result
    }
}