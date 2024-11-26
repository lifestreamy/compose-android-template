import com.github.compose_android_template.configs.*

plugins {
    alias(libs.plugins.compose.android.template.android.application)
    alias(libs.plugins.compose.android.template.android.application.compose)
    alias(libs.plugins.compose.android.template.android.application.flavors)
}

android {
    namespace = "com.github.compose_android_template"

    defaultConfig {
        applicationId = APPLICATION_ID

        versionCode = VERSION_CODE
        versionName = VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        /*Android application (APK) files contain executable bytecode files
         * in the form of Dalvik Executable (DEX) files, which contain the compiled
         * code used to run your app. The Dalvik Executable specification limits
         * the total number of methods that can be referenced within a single DEX file to 65,536
         * , including Android framework methods, library methods, and methods in your own code.
         * Getting past this limit requires that you configure your app build process to generate
         *  more than one DEX file, known as a multidex configuration.
         * https://developer.android.com/build/multidex
         * P.S. : required to be enabled for core library desugaring. */
//        multiDexEnabled = true //


        vectorDrawables {
            useSupportLibrary = true
        }
    }


    buildTypes {
        debug {
            applicationIdSuffix = ProjectBuildType.DEBUG.applicationIdSuffix
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            applicationIdSuffix = ProjectBuildType.RELEASE.applicationIdSuffix
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            // To publish on the Play store a private signing key is required, but to allow anyone
            // who clones the code to sign and run the release variant, use the debug signing key.
            signingConfig = signingConfigs.named(BuildVariantSigningKey.DEBUG.key).get()
            // Ensure Baseline Profile is fresh for release builds. Use when baseline profiles are used for the app.
//            baselineProfile.automaticGenerationDuringBuild = true
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform() // Enables JUnit Platform (JUnit 5 + JUnit 4)
    minHeapSize = "512m"
    maxHeapSize = "2048m"
    jvmArgs = listOf("-XX:MaxPermSize=2048m")
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.compose.activity)
    implementation(platform(libs.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}