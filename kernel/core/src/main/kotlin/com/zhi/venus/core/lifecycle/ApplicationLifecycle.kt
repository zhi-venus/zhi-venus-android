package com.zhi.venus.core.lifecycle

import android.content.Context
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.Multibinds

fun interface AppCreateCallback {
    fun onAppCreate(context: Context)
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class AppCallbackModule {

    @Multibinds
    abstract fun bindsAppCreateCallbacks(): Set<AppCreateCallback>
}
