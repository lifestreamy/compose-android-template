package com.github.compose_android_template.plugins

import com.android.build.api.dsl.ApplicationExtension
import com.github.compose_android_template.configs.*
import com.github.compose_android_template.configs.configureGradleManagedDevices
import com.github.compose_android_template.configs.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }
            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = DEFAULT_TARGET_SDK
                @Suppress("UnstableApiUsage")
                testOptions.animationsDisabled = true
                configureGradleManagedDevices(this)
            }

        }
    }
}
