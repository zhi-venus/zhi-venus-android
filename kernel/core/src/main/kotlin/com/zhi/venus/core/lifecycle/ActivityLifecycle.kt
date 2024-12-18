package com.zhi.venus.core.lifecycle

import android.app.Activity
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.Multibinds
import javax.inject.Inject

class ActivityLifecycleObserver @Inject constructor(
    activity: Activity,
    lifecycleObservers: Set<@JvmSuppressWildcards(true) LifecycleObserver>,
) {
    init {
        lifecycleObservers.forEach {
            (activity as? LifecycleOwner)?.lifecycle?.addObserver(it)
        }
    }
}

@Module
@InstallIn(ActivityComponent::class)
abstract class ActivityLifecycleObserverModule {

    @Multibinds
    abstract fun bindsObservers(): Set<LifecycleObserver>
}
