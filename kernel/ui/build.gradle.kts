plugins {
    alias(libs.plugins.venus.android.library)
    alias(libs.plugins.venus.android.library.compose)
}

android {
    namespace = "com.zhi.venus.ui"
}

dependencies {
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.material3.adaptive)
    api(libs.androidx.compose.material3.navigationSuite)

    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
}
