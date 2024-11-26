package com.github.compose_android_template.plugins

import com.android.build.api.dsl.LibraryExtension
import com.github.compose_android_template.configs.configureAndroidTestingDepsJunit
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType

/**
 * To be used along with [AndroidLibraryConventionPlugin] in plugins {}
 * */
class AndroidTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
//                apply("com.android.test") does not work with library plugin, create a separate plugin if needed.
                apply("org.jetbrains.kotlin.android")
//                apply(getDefaultLibs().findPlugin("junit5.plugin").get())
            }
            tasks.withType<Test> { useJUnitPlatform() }
            extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
            }
            target.configureAndroidTestingDepsJunit()

        }
    }

}
