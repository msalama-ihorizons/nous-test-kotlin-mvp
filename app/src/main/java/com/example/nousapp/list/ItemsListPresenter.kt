package com.example.nousapp.list

import android.os.AsyncTask
import com.example.nousapp.data.model.NousResponse
import com.example.robustaweather.OnFinishedListener

class ItemsListPresenter(
    view: ItemsListContract.View?,
    itemsListFetcher: ItemsListFetcher?) : ItemsListContract.UserActionsListener {

    private var view: ItemsListContract.View? = null
    private var itemsListFetcher: ItemsListFetcher? = null

    init {
        this.view = view
        this.itemsListFetcher = itemsListFetcher
    }


    override fun loadItems() {
        view?.showProgress()

        itemsListFetcher?.getItems(
            object : OnFinishedListener<NousResponse> {
                override fun onSuccess(data: NousResponse?) {
                    view?.showItemsList(data?.items)
                }

                override fun onFailure(errorMessage: String?) {
                    view?.showFailureMessage(errorMessage)
                }

                override fun onComplete() {
                    view?.hideProgress()
                }

            }
        )
    }

}