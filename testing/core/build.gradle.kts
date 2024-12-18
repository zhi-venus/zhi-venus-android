plugins {
    alias(libs.plugins.venus.android.library)
    alias(libs.plugins.venus.hilt)
}

android {
    namespace = "com.zhi.venus.testing.core"
}

dependencies {
    implementation(libs.androidx.test.runner)
    implementation(libs.androidx.test.core)
    implementation(libs.androidx.test.monitor)
    implementation(libs.hilt.android.testing)
}
