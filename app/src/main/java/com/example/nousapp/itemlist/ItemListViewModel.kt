package com.example.nousapp.itemlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nousapp.data.model.NousResponse
import com.example.nousapp.data.model.Resource

class ItemListViewModel : ViewModel() {

    private var  itemsListFetcher: ItemsListFetcher = ItemsListFetcher()
    private var itemsLiveData: LiveData<Resource<NousResponse>>

    init {
        itemsLiveData = itemsListFetcher.getItems()
    }

    fun getItemsLiveData(): LiveData<Resource<NousResponse>> = itemsLiveData
}