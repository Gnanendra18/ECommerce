package com.incrediblepath.architecturecomponents.api

import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor:Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val  request = chain.request()
        request.newBuilder().addHeader("Authorization","Bearer Some jwt token or any token for authenticating api")
        return chain.proceed(request)
    }
}