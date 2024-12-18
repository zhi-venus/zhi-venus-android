# Project/App Quanlity

[![EN](https://img.shields.io/badge/lang-EN-blue)](project-quality.md)
[![ZH](https://img.shields.io/badge/lang-ZH-red)](project-quality.zh.md)

We're following the [Google's Android App Quality Guide](https://developer.android.com/distribute/best-practices/develop/quality) to improve the quality of our app.

Project quality, or app quality, or technial quanlity includes the stability, performance, and resource utilization of the app.



## [JankStats Libray][1]

The JankStats libarary can help up track and analyze performace issues in our app. `Jank heristics` can helper us to determin when jank happens, and then report to us with more contextualze information.


### How to use it in venus ? 

In Venus, we define a simple JankStats reporting framework with Hilt's dependency injection tools.

+ For activity entry point.

If a `JankStatsReporter` is provided, we will track the jank frames and report to it. Otherwise, we will just print the jank frames to consonle.

The following core hilt module is provided in Venus:

```kotlin
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
        ): LifecycleObserver = object : DefaultLifecycleObserver {
            override fun onResume(owner: LifecycleOwner) {
                lazyStats.get().isTrackingEnabled = true
            }

            override fun onPause(owner: LifecycleOwner) {
                lazyStats.get().isTrackingEnabled = false
            }
        }
    }
}

```

A custom `JankStatsReporter` can be provided in app's debug build type, as follows:

```kotlin
@Module
@InstallIn(ActivityComponent::class)
internal object JankStatsModule {

    @Provides
    @ActivityScoped
    fun provideJankStatsLogger() = object : JankStatsReporter {

        override fun report(frameData: FrameData) {
            Log.v("Nia-Jank", frameData.toString())
        }
    }
}

```

+ For PerformanceMetricsState

That stores the information of an application which can be retrieved later to associate state with performance timing data.

1. Stores the [PerformanceMetricsState.Holder] in LocalView

```kotlin
@Composable
private fun rememberMetricsStateHolder(): Holder {
    val localView = LocalView.current

    return remember(localView) {
        PerformanceMetricsState.getHolderForHierarchy(localView)
    }
}
```

2. `TrackJank`

Convenient functions to work with [PerformanceMetricsState] state. The side effect is re-launched if any of the [keys] value is not equal to the previous composition.

```kotlin
@Composable
fun TrackJank(
    vararg keys: Any,
    reportMetric: suspend CoroutineScope.(state: Holder) -> Unit,
) {
    val metrics = rememberMetricsStateHolder()
    LaunchedEffect(metrics, *keys) {
        reportMetric(metrics)
    }
}
```


3. `TrackDisposableJank`

Worked same as `TrackJank`, but it use `DisposableEffectScope` and user can cleanup added state.

```kotlin
@Composable
fun TrackDisposableJank(
    vararg keys: Any,
    reportMetric: DisposableEffectScope.(state: Holder) -> DisposableEffectResult,
) {
    val metrics = rememberMetricsStateHolder()
    DisposableEffect(metrics, *keys) {
        reportMetric(this, metrics)
    }
}
```


[1]:https://developer.android.com/topic/performance/jankstats