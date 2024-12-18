package com.zhi.venus.performance.jank

import android.app.Activity
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.metrics.performance.JankStats
import dagger.BindsOptionalOf
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.multibindings.IntoSet
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

@Module
@InstallIn(ActivityComponent::class)
internal abstract class JankStatsModule {

    @BindsOptionalOf
    abstract fun bindOptionalReporter(): JankStatsReporter

    companion object {
        @Provides
        fun providesJankStats(
            activity: Activity,
            @ActivityScoped reporter: Optional<JankStatsReporter>,
        ): JankStats = JankStats.createAndTrack(activity.window) { frameData ->
            frameData.takeIf { it.isJank }?.let {
                reporter.getOrNull()?.report(frameData)
                    ?: Log.v("Venus Jank", frameData.toString())
            }
        }

        @Provides
        @IntoSet
        fun providesLifecycleObserver(
            lazyStats: dagger.Lazy<JankStats>,
            @ActivityScoped reporter: Optional<JankStatsReporter>,
        ): LifecycleObserver = object : DefaultLifecycleObserver {
            override fun onResume(owner: LifecycleOwner) {
                lazyStats.get().isTrackingEnabled = true
                reporter.getOrNull()?.track(true)
            }

            override fun onPause(owner: LifecycleOwner) {
                lazyStats.get().isTrackingEnabled = false
                reporter.getOrNull()?.track(false)
            }
        }
    }
}
