import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.github.compose_android_template.buildlogic"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
    implementation(libs.truth)
    compileOnly(libs.junit5.plugin)
}

tasks {
    validatePlugins {
        failOnWarning = true
        enableStricterValidation = true
    }
}

gradlePlugin {
    plugins {
        val pluginsPackage = "com.github.compose_android_template.plugins."
        register("androidApplication") {
            id = "compose_android_template.android.application"
            implementationClass = pluginsPackage + "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "compose_android_template.android.application.compose"
            implementationClass = pluginsPackage + "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "compose_android_template.android.library"
            implementationClass = pluginsPackage + "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "compose_android_template.android.library.compose"
            implementationClass = pluginsPackage + "AndroidLibraryComposeConventionPlugin"
        }
        register("androidFeature") {
            id = "compose_android_template.android.feature"
            implementationClass = pluginsPackage + "AndroidFeatureConventionPlugin"
        }
        register("androidTest") {
            id = "compose_android_template.android.test"
            implementationClass = pluginsPackage + "AndroidTestConventionPlugin"
        }
        register("androidHilt") {
            id = "compose_android_template.android.hilt"
            implementationClass = pluginsPackage + "AndroidHiltConventionPlugin"
        }
        register("androidRoom") {
            id = "compose_android_template.android.room"
            implementationClass = pluginsPackage + "AndroidRoomConventionPlugin"
        }
        register("androidFlavors") {
            id = "compose_android_template.android.application.flavors"
            implementationClass = pluginsPackage + "AndroidApplicationFlavorsConventionPlugin"
        }
        register("kotlinLibrary") {
            id = "compose_android_template.kotlin.library"
            implementationClass = pluginsPackage + "KotlinLibraryConventionPlugin"
        }
    }
}