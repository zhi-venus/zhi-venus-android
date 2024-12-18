package com.zhi.venus.network

import androidx.tracing.trace
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.Multibinds
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonBuilder
import javax.inject.Singleton

/**
 *  The Builder is used to configure app scope [Json] instance.
 *  Provided instance should be qualified with [NetContext]
 */
fun interface JsonModuleBuilder {
    fun build(builder: JsonBuilder)
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class JsonModule {
    @Multibinds
    @NetContext
    @Singleton
    fun provideJson(
        @NetContext builders: Set<@JvmSuppressWildcards(true) JsonModuleBuilder>,
    ): Json = trace("JsonModule.provideJson") {
        Json {
            builders.forEach {
                it.build(this)
            }
        }
    }
}
