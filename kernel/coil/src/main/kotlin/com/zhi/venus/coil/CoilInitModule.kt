package com.zhi.venus.coil

import coil.decode.SvgDecoder.Factory
import coil.util.DebugLogger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
internal object CoilInitModule {
    @Provides
    @IntoSet
    fun provideComponentRegisterBuildCallback() = ComponentRegisterBuilder {
        // Coil needs an ImageLoader which supports svg format.
        it.add(Factory())
    }

    @Provides
    @IntoSet
    fun provideImageLoaderBuildCallback() = ImageLoaderBuilder {
        // Assume most content images are versioned urls
        // but some problematic images are fetching each time
        it.respectCacheHeaders(false)
            .takeIf { BuildConfig.DEBUG }
            ?.apply {
                logger(DebugLogger())
            }
    }
}
