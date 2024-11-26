pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") } // Remove if not using jitpack.io for dependencies
    }
    versionCatalogs {
    }
}


rootProject.name = "compose_android_template"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")

check(JavaVersion.current().isCompatibleWith(JavaVersion.VERSION_17)) {
    """
    This application requires JDK 17+ but it is currently using JDK ${JavaVersion.current()}.
    Java Home: [${System.getProperty("java.home")}]
    https://developer.android.com/build/jdks#jdk-config-in-studio
    """.trimIndent()
}
