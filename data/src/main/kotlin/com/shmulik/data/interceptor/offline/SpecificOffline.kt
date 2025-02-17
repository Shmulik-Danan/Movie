package com.shmulik.data.interceptor.offline

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

enum class SpecificOfflineState { OFFLINE }


@Retention(RetentionPolicy.RUNTIME)
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
annotation class SpecificOffline(val cachingMode: SpecificOfflineState)