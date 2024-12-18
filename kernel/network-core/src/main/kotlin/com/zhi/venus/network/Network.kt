package com.zhi.venus.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationTarget.FUNCTION
import kotlin.annotation.AnnotationTarget.VALUE_PARAMETER

@Qualifier
@Target(FUNCTION, VALUE_PARAMETER)
annotation class NetContext
