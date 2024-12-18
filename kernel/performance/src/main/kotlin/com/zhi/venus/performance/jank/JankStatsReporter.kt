package com.zhi.venus.performance.jank

import androidx.metrics.performance.FrameData
import androidx.metrics.performance.JankStats

/**
 * Utility used to report [JankStats] reported [FrameData].
 * Concrete instance can have different strategies to report collected information,
 * singled, batched, or even timed!
 */
interface JankStatsReporter {

    fun report(frameData: FrameData)

    fun track(enabled: Boolean) {}
}
