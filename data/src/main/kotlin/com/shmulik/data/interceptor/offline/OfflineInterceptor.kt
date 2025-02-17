package com.shmulik.data.interceptor.offline


import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import retrofit2.Invocation



class OfflineInterceptor : Interceptor {
    var minResponseTime = Long.MAX_VALUE


    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()

        val specificOffline = request.tag(Invocation::class.java)?.method()
            ?.getAnnotation(SpecificOffline::class.java)

        specificOffline ?: return chain.proceed(request)

        println("maxStale = ${specificOffline.cachingMode}")
        // Offline cache available for 60 second or 10 min or one day
        val maxStale = when (specificOffline.cachingMode) {
            SpecificOfflineState.OFFLINE -> 60 * 60 * 24
        }

        request = request.newBuilder()
            .header("Cache-Control", "public, max-stale=$maxStale")
            .removeHeader("Pragma")
            .build()

        return chain.proceed(request)
    }

}