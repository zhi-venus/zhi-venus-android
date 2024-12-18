package com.zhi.venus.core.coroutine

import com.zhi.venus.core.coroutine.Dispatchers.Default
import com.zhi.venus.core.coroutine.Dispatchers.IO
import com.zhi.venus.core.coroutine.Dispatchers.Main
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

enum class Dispatchers {
    Default,
    IO,
    Main,
}

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val dispatchers: Dispatchers)

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
    @Provides
    @Dispatcher(IO)
    fun providesIODispatcher(): CoroutineDispatcher = kotlinx.coroutines.Dispatchers.IO

    @Provides
    @Dispatcher(Default)
    fun providesDefaultDispatcher(): CoroutineDispatcher = kotlinx.coroutines.Dispatchers.Default

    @Provides
    @Dispatcher(Main)
    fun providesMainDispatcher(): CoroutineDispatcher = kotlinx.coroutines.Dispatchers.Main
}
