plugins {
    alias(libs.plugins.venus.android.library)
    alias(libs.plugins.venus.hilt)
}

android {
    namespace = "com.zhi.venus.testing.hilt.workaround"
}

dependencies {
    implementation(libs.hilt.android.testing)
}
