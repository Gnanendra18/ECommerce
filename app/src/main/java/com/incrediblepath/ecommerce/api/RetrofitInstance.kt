package com.incrediblepath.architecturecomponents.api

import com.incrediblepath.architecturecomponents.utils.NetworkConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val client = OkHttpClient.Builder().apply {
        addInterceptor(MyInterceptor())
    }.build()

    private val retrofit:Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(NetworkConstants.ROOT_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val myApi:SampleApi by lazy {
        retrofit.create(SampleApi::class.java)
    }
}