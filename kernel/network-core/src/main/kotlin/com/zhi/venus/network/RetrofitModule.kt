package com.zhi.venus.network

import androidx.tracing.trace
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.Multibinds
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

/** The Builder is used to configure [Retrofit] */
fun interface RetrofitBuilder {
    fun build(builder: Retrofit.Builder)
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RetrofitModule {
    @Multibinds
    @NetContext
    abstract fun bindsRetrofitBuilders(): Set<RetrofitBuilder>

    companion object {
        @Provides
        @NetContext
        fun provideJson(): Json = Json {
            ignoreUnknownKeys = true
        }

        @Provides
        @NetContext
        @Singleton
        fun provideRetrofit(
            @NetContext client: dagger.Lazy<OkHttpClient>,
            @NetContext builders: Set<@JvmSuppressWildcards(true) RetrofitBuilder>,
        ): Retrofit = trace("RetrofitModule.provideRetrofit") {
            Retrofit.Builder().apply {
                // add a place holder. follow can replace it in some [RetrofitBuilder]
                baseUrl("http://venus.zhi.com")

                // client(client.get()) // time-consuming when initialize
                // We use callFactory lambda here with dagger.Lazy<Call.Factory>
                // to prevent initializing OkHttp on the main thread.
                callFactory { client.get().newCall(it) }

                builders.forEach {
                    it.build(this)
                }
            }.build()
        }
    }
}
