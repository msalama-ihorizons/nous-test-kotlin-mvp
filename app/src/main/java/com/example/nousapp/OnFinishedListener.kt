package com.example.nousapp


interface OnFinishedListener<T> {
    fun onSuccess(data: T?)
    fun onFailure(errorMessage: String?)
    fun onComplete()
}