package com.example.nousapp.itemlist

import android.util.Log
import androidx.lifecycle.*
import com.example.nousapp.data.ApiEmptyResponse
import com.example.nousapp.data.ApiErrorResponse
import com.example.nousapp.data.model.NousResponse
import com.example.nousapp.data.model.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "ItemListViewModel"
class ItemListViewModel(serviceNumber: String) : ViewModel() {

    private val loadTrigger = MutableLiveData(Unit)

    private var  itemsListFetcher: ItemsListFetcher = ItemsListFetcher()

    val itemsLiveData = MutableLiveData<Resource<NousResponse>>();


    init {
        Log.i(TAG, "InitView Model "+ serviceNumber)
        getItems(serviceNumber)
    }

  /*  private var itemsLiveData: LiveData<Resource<NousResponse>> =
        Transformations.switchMap(loadTrigger) {
            itemsListFetcher.getItems()
        }

    fun refresh() {
        loadTrigger.value = Unit
    }

    fun getItemsLiveData(): LiveData<Resource<NousResponse>> = itemsLiveData*/

   /* fun getItemsLiveData() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = itemsListFetcher.getItems()))
            emit(Resource.complete(null))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, msg = exception.message ?: "Error Occurred!"))
        }
    }*/


/*    fun getItems() {
        itemsLiveData.value = Resource.loading(data = null)

        viewModelScope.launch {

            val result: ApiResponse<NousResponse> = itemsListFetcher.getItems()

            Resource.complete(null)

            when (result) {
                is ApiSuccessResponse -> itemsLiveData.value = Resource.success(result.body)
                is ApiEmptyResponse ->  itemsLiveData.value = Resource.success(null)
                is ApiErrorResponse ->  itemsLiveData.value = Resource.error(result.errorMessage, null);
            }

        }
    }*/

    fun getItems(string: String) {

        Log.i(TAG, "getItems "+ string)

        itemsLiveData.value = Resource.loading(data = null)

        viewModelScope.launch {

            try {

                val result: NousResponse = itemsListFetcher.getItems()
                itemsLiveData.value = Resource.complete(null)

                itemsLiveData.value = Resource.success(result)
            } catch (e: Exception) {
                itemsLiveData.value = Resource.complete(null)
                itemsLiveData.value = Resource.error(e.message, null)
            }

        }
    }
}