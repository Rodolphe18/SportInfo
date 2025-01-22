package com.francotte.android.sportinfo.data.remote.api

import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader(API_KEY_NAME, API_KEY_VALUE)
            .build()
        return chain.proceed(request)
    }

    companion object {
        const val API_KEY_NAME = "X-Auth-Token"
        const val API_KEY_VALUE = "5cc37b8cb8e543d09b8f8fd4c48610fb"
    }

}