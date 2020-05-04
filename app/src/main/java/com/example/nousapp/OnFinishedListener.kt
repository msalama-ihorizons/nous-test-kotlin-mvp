package com.example.robustaweather


interface OnFinishedListener<T> {
    fun onSuccess(data: T?)
    fun onFailure(errorMessage: String?)
    fun onComplete()
}