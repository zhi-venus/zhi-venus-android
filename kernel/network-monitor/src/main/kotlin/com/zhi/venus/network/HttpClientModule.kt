package com.zhi.venus.network

import androidx.tracing.trace
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.Multibinds
import okhttp3.OkHttpClient
import javax.inject.Singleton

/**
 * The Builder is used to configure [OkHttpClient].
 * Provided instance should be qualified with [NetContext]
 * @see [HttpClientInitModule]
 */
fun interface HttpClientBuilder {
    fun build(builder: OkHttpClient.Builder)
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class HttpClientModule {
    @Multibinds
    @NetContext
    abstract fun bindsHttpClientBuilders(): Set<HttpClientBuilder>

    companion object {
        @Provides
        @NetContext
        @Singleton
        fun provideHttpClient(
            @NetContext builders: Set<@JvmSuppressWildcards(true) HttpClientBuilder>,
        ) = trace("HttpClientModule.provideHttpClient") {
            OkHttpClient.Builder().apply {
                builders.forEach {
                    it.build(this)
                }
            }.build()
        }
    }
}

