package com.zhi.venus.network

import com.zhi.venus.network.core.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY

@Module
@InstallIn(SingletonComponent::class)
internal object HttpClientInitModule {
    @Provides
    @IntoSet
    @NetContext
    fun provideHttpLoggingInterceptor() = HttpClientBuilder {
        // add http logger interceptor and it also provides a demo to add [HttpClientBuilder]
        it.addInterceptor(
            HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG) {
                    setLevel(BODY)
                }
            },
        )
    }
}
