pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "venus"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":lint")
project(":lint").projectDir = file("tools/lint")

include(":kernel:analytics")
include(":kernel:coil")
include(":kernel:core")
include(":kernel:hilt")
include(":kernel:hilt-test")
include(":kernel:network-core")
include(":kernel:network-monitor")
include(":kernel:network-monitor-test")
include(":kernel:performance")
include(":kernel:ui")

include(":testing:core")
include(":testing:hilt-workaround")
include(":testing:screenshot")

// Auto add project module for apps director.
fileTree(file("${rootProject.projectDir}/apps")) {
    include("*/installedProjects.gradle.kts")
}.forEach { file ->
    apply(from = file)
}

check(JavaVersion.current().isCompatibleWith(JavaVersion.VERSION_17)) {
    """
    Now in Android requires JDK 17+ but it is currently using JDK ${JavaVersion.current()}.
    Java Home: [${System.getProperty("java.home")}]
    https://developer.android.com/build/jdks#jdk-config-in-studio
    """.trimIndent()
}
