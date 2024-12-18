plugins {
    alias(libs.plugins.venus.android.library)
    alias(libs.plugins.venus.hilt)
}

android {
    namespace = "com.zhi.venus.coil"
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    api(libs.coil.kt.compose)
    api(libs.coil.kt.svg)
    implementation(projects.kernel.hilt)
    implementation(projects.kernel.networkCore)
}
