package com.shmulik.data.interceptor.online

import com.shmulik.data.interceptor.offline.SpecificOffline
import com.shmulik.data.interceptor.offline.SpecificOfflineState
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import retrofit2.Invocation


class OnlineInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        return response.newBuilder()
            .header("Cache-Control", "public, max-age=60")
            .removeHeader("Pragma")
            .build()
    }
}
