package com.github.compose_android_template.configs

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

fun Project.getDefaultLibs(): VersionCatalog =
    extensions.getByType(VersionCatalogsExtension::class).named("libs")
