package com.zhi.venus.coil

import android.content.Context
import androidx.compose.ui.util.trace
import coil.Coil
import coil.ComponentRegistry
import coil.ImageLoader
import com.zhi.venus.hilt.lifecycle.AppCreateCallback
import com.zhi.venus.network.NetContext
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import dagger.multibindings.Multibinds
import okhttp3.OkHttpClient
import javax.inject.Singleton

/** The builder fun used to config a [ImageLoader] */
fun interface ImageLoaderBuilder {
    fun build(builder: ImageLoader.Builder)
}

/** The builder fun used to config a [ComponentRegistry] */
fun interface ComponentRegisterBuilder {
    fun build(builder: ComponentRegistry.Builder)
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class CoilModule {
    @Multibinds
    abstract fun bindImageLoaderBuilders(): Set<ImageLoaderBuilder>

    @Multibinds
    abstract fun bindComponentRegisterBuilders(): Set<ComponentRegisterBuilder>

    companion object {

        @Provides
        @Singleton
        fun provideImageLoader(
            @ApplicationContext context: Context,
            @NetContext httpClient: OkHttpClient,
            ilBuilders: Set<@JvmSuppressWildcards ImageLoaderBuilder>,
            crBuilders: Set<@JvmSuppressWildcards ComponentRegisterBuilder>,
        ) = trace("provideAppCreateCallbackCreateImageLoader") {
            ImageLoader.Builder(context)
                .callFactory { httpClient }
                .components {
                    crBuilders.forEach { it.build(this) }
                }
                .also { builder ->
                    ilBuilders.forEach { it.build(builder) }
                }
                .build()
                .also {
                    Coil.setImageLoader { it }
                }
        }

        @Provides
        @IntoSet
        fun provideAppCreateCallbackCreateImageLoader(
            imageLoader: dagger.Lazy<ImageLoader>,
        ) = AppCreateCallback {
            Coil.setImageLoader {
                imageLoader.get()
            }
        }
    }
}

