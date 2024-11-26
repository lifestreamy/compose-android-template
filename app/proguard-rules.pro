# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# General rules to keep annotations and signatures
-keepattributes *Annotation*
-keepattributes Signature

#-keep class sun.misc.Unsafe { *; }

# Preserve Kotlin serialization (@Serializable)
-keepclasseswithmembers class * {
    @kotlinx.serialization.Serializable <fields>;
}
-keep @kotlinx.serialization.Serializable class * { *; }

# Preserve Room database entities and DAOs
-keepclasseswithmembers class * {
    @androidx.room.Entity <fields>;
    @androidx.room.Dao <methods>;
    @androidx.room.Database <methods>;
    @androidx.room.Query <methods>;
}

# Preserve Room entities, DAOs and related classes
-keep class androidx.room.** { *; }
-keep @interface androidx.room.* { *; }

# Preserve classes annotated with Room annotations
-keepclassmembers class * {
    @androidx.room.* <methods>;
    @androidx.room.* <fields>;
    @androidx.room.* <init>();
    @androidx.room.* <clinit>();
}

-keep class kotlin.Metadata { *; }
-keep class kotlinx.** { *; }
-keep class com.github.compose_android_template.** { *; }  # Ensure your package is not stripped or obfuscated within your own DTOs/entities.

-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-dontwarn androidx.room.paging.**
-keep @androidx.room.Entity class *

-keepclassmembers,allowobfuscation class * {
@com.google.gson.annotations.SerializedName <fields>;
}


# Preserve Parcelable implementations
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# Gson Annotations
-keep class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# Enable general reflection for custom models (if any)
-keepclassmembers class * {
    @com.github.compose_android_template.* <fields>;
}

# Retrofit
-keep class * {
    @retrofit2.http.* <methods>;
}
-keep interface * {
    @retrofit2.http.* <methods>;
}
-keepclassmembers class * {
    @retrofit2.http.* <methods>;
}
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}


# Fix for Retrofit issue https://github.com/square/retrofit/issues/3751
# Keep generic signature of Call, Response (R8 full mode strips signatures from non-kept items).
-keep,allowobfuscation,allowshrinking interface retrofit2.Call
-keep,allowobfuscation,allowshrinking class retrofit2.Response

# With R8 full mode generic signatures are stripped for classes that are not
# kept. Suspend functions are wrapped in continuations where the type argument
# is used.
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

# Preserve Moshi and Gson serialization annotations
-keepclasseswithmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# Special rule for Kotlin classes
-keep class kotlin.Metadata { *; }