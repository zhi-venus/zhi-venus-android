package com.zhi.venus.hilt.coroutine.test

import com.zhi.venus.hilt.coroutine.Dispatcher
import com.zhi.venus.hilt.coroutine.Dispatchers.Default
import com.zhi.venus.hilt.coroutine.Dispatchers.IO
import com.zhi.venus.hilt.coroutine.DispatchersModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object TestDispatcherModule {
    @Provides
    @Singleton
    fun providesTestDispatcher(): TestDispatcher = UnconfinedTestDispatcher()
}

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DispatchersModule::class],
)
internal interface TestDispatchersModule {
    @Binds
    @Dispatcher(IO)
    fun providesIODispatcher(
        testDispatcher: TestDispatcher,
    ): CoroutineDispatcher

    @Binds
    @Dispatcher(Default)
    fun providesDefaultDispatcher(
        testDispatcher: TestDispatcher,
    ): CoroutineDispatcher
}
