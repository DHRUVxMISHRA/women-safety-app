package com.example.womensafetyapp.network

import com.example.womensafetyapp.utils.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiProvider {


    private val okHttpClient = OkHttpClient.Builder().connectTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
        .readTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
        .writeTimeout(60, java.util.concurrent.TimeUnit.SECONDS)

    fun provideApi() : ApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

}