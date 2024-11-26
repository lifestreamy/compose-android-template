package com.github.compose_android_template.configs

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

/**
 * Configure Compose-specific options
 */
internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    val libs = getDefaultLibs()
    commonExtension.apply {
        buildFeatures {
            compose = true
        }
            /**Not needed if you are using Kotlin 2+ with ComposeCompilerPlugin */
//        composeOptions {
//            kotlinCompilerExtensionVersion =
//                libs.findVersion("androidxComposeCompiler").get().toString()
//        }

        dependencies {
            val bom = libs.findLibrary("compose-bom").get()
            add("implementation", platform(bom))
            add("androidTestImplementation", platform(bom))
            add("implementation", libs.findLibrary("compose-ui-tooling-preview").get())
            add("debugImplementation", libs.findLibrary("compose-ui-tooling").get())
        }

        testOptions {
            unitTests {
                // For Robolectric
                isIncludeAndroidResources = true
            }
        }
    }


    extensions.configure<ComposeCompilerGradlePluginExtension> {
        fun Provider<String>.trueOrNull() = flatMap { string ->
            provider { string.takeIf { it.toBoolean() } }
        }

        fun Provider<*>.toDirProviderRelativeToRoot(dir: String) = flatMap {
            rootProject.layout.buildDirectory.dir(projectDir.toRelativeString(rootDir))
        }.map { directory -> directory.dir(dir) }


        project.providers.gradleProperty("enableComposeCompilerMetrics").trueOrNull()
            .toDirProviderRelativeToRoot("compose-metrics").let { metricsDestination.set(it)}

        project.providers.gradleProperty("enableComposeCompilerReports").trueOrNull()
            .toDirProviderRelativeToRoot("compose-reports").let { reportsDestination.set(it)}

        stabilityConfigurationFile =
            rootProject.layout.projectDirectory.file("compose_compiler_config.conf")
    }

    /** Use instead of the above if you are using Kotlin version below 2.0, prior to the ComposeCompilerPlugin requirement */
//    tasks.withType<KotlinCompile>().configureEach {
//        compilerOptions {
//            freeCompilerArgs.addAll(buildComposeMetricsParameters())
//            freeCompilerArgs.addAll(stabilityConfiguration())
//        }
//    }


}

/** Use with KotlinCompile task if you are using Kotlin version below 2.0, prior to the ComposeCompilerPlugin requirement */
private fun Project.buildComposeMetricsParameters(): List<String> {
    // Compose Compiler Metrics, saved to a file with this path
    val metricParameters = mutableListOf<String>()
    val enableMetricsProvider = project.providers.gradleProperty("enableComposeCompilerMetrics")
    val relativePath = projectDir.relativeTo(rootDir)
    val buildDir = layout.buildDirectory.get().asFile
    val enableMetrics = (enableMetricsProvider.orNull == "true")
    if (enableMetrics) {
        val metricsFolder = buildDir.resolve("compose-metrics").resolve(relativePath)
        metricParameters.add("-P")
        metricParameters.add(
            "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" + metricsFolder.absolutePath,
        )
    }

    // Compose compiler reports, saved to a file with this path
    val enableReportsProvider = project.providers.gradleProperty("enableComposeCompilerReports")
    val enableReports = (enableReportsProvider.orNull == "true")
    if (enableReports) {
        val reportsFolder = buildDir.resolve("compose-reports").resolve(relativePath)
        metricParameters.add("-P")
        metricParameters.add(
            "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" + reportsFolder.absolutePath
        )
    }
    return metricParameters.toList()
}

/**
 *  A file used to declare classes, directories or whole modules of yours or of 3-rd party libs as @Stable for the Compose Compiler
 * */
private fun Project.stabilityConfiguration() = listOf(
    "-P",
    "plugin:androidx.compose.compiler.plugins.kotlin:stabilityConfigurationPath=${project.rootDir.absolutePath}/compose_compiler_config.conf",
)
