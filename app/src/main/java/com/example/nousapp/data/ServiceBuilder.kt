package com.example.robustaweather.data

import com.android.example.github.util.LiveDataCallAdapterFactory
import com.example.nousapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ServiceBuilder {

    object ServiceBuilder {
        private val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.END_POINT)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()

        fun<T> buildService(service: Class<T>): T{
            return retrofit.create(service)
        }
    }
}