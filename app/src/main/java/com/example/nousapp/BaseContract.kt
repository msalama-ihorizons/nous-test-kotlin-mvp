package com.example.nousapp

interface BaseContract {
    interface View<T> {
        fun hideProgress()
        fun showProgress()
        fun showFailureMessage(errorMessage: String?)
    }
}