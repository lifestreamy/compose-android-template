package com.github.compose_android_template.configs

/**
 * Shared between :app and :benchmarks module to provide configurations type safety.
 */
enum class ProjectBuildType(val applicationIdSuffix: String? = null) {
    DEBUG(".debug"),
    RELEASE
}
