package com.shmulik.data.util

import android.util.Log
import com.shmulik.domain.util.DataResult


suspend fun <T> safeApiCall(apiCall: suspend () -> T): DataResult<T> {
    return try {
        DataResult.Success(apiCall.invoke())
    } catch (throwable: Throwable) {
        Log.d("safeApiCall", throwable.message ?: "")
        DataResult.Error(throwable)
    }

}