package com.example.nousapp.list

import com.example.nousapp.data.model.Item
import com.example.robustaweather.BaseContract

interface ItemsListContract {

    interface View: BaseContract.View<Any?> {
        fun showItemsList(
            items: List<Item>?
        )
    }

    interface UserActionsListener {
        fun loadItems()
    }
}