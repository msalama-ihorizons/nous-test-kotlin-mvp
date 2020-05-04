package com.example.nousapp.itemlist

import com.example.nousapp.BaseContract
import com.example.nousapp.data.model.Item

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