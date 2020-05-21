package com.example.nousapp.itemlist

import android.app.Application
import androidx.lifecycle.*
import com.example.nousapp.data.model.NousResponse
import com.example.nousapp.data.model.Resource

class ItemListViewModel : ViewModel() {

    private val loadTrigger = MutableLiveData(Unit)

    private var  itemsListFetcher: ItemsListFetcher = ItemsListFetcher()

    private var itemsLiveData: LiveData<Resource<NousResponse>> =
        Transformations.switchMap(loadTrigger) {
            itemsListFetcher.getItems()
        }

    fun refresh() {
        loadTrigger.value = Unit
    }

    fun getItemsLiveData(): LiveData<Resource<NousResponse>> = itemsLiveData
}