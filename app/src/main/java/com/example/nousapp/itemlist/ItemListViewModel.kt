package com.example.nousapp.itemlist

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.nousapp.data.model.NousResponse
import com.example.nousapp.data.model.Resource

private const val TAG = "ItemListViewModel"

class ItemListViewModel : ViewModel() {

    private val loadTrigger = MutableLiveData(Unit)
    private lateinit var itemsLiveData: MutableLiveData<Resource<NousResponse>>

    private var  itemsListFetcher: ItemsListFetcher = ItemsListFetcher()

    init {

        Log.i(TAG, "InitView Model")
        itemsLiveData = itemsListFetcher.getItems()
    }
/*
    private var itemsLiveData: LiveData<Resource<NousResponse>> =
        Transformations.switchMap(loadTrigger) {
            itemsListFetcher.getItems()
        }*/
/*private var itemsLiveData: LiveData<Resource<NousResponse>> =itemsListFetcher.getItems()
    fun refresh() {
        loadTrigger.value = Unit
    }*/

    fun getItemsLiveData(): LiveData<Resource<NousResponse>> = itemsLiveData
}