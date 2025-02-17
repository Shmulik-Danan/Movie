package com.shmulik.domain.util


sealed class DataResult<T> {
    data class Success<T>(val data: T) : DataResult<T>()
    data class Error<T>(val error: Throwable) : DataResult<T>()
}

inline fun <T, R> DataResult<T>.getResult(
    success: (DataResult.Success<T>) -> R,
    error: (DataResult.Error<T>) -> R
): R = when (this) {
    is DataResult.Success -> success(this)
    is DataResult.Error -> error(this)
}

inline fun <T> DataResult<T>.onSuccess(
    block: (T) -> Unit
): DataResult<T> = if (this is DataResult.Success) also { block(data) } else this

inline fun <T> DataResult<T>.onError(
    block: (Throwable) -> Unit
): DataResult<T> = if (this is DataResult.Error) also { block(error) } else this

fun <T> DataResult<T>.asSuccessValueOrNull(): T? = (this as? DataResult.Success)?.data

fun <T> DataResult<T>.asSuccessValue(): T = (this as DataResult.Success).data